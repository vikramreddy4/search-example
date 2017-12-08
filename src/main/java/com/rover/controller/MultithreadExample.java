package com.rover.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

public class MultithreadExample {

	public List<Integer> processTask(Integer count) {
		List<Integer> numbers = new ArrayList<Integer>(count);
		Queue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

		try {
			for(int i = 1; i<=count;i++) {
				numbers.add(i);
				queue.add(i);
			}

	        final ExecutorService executor = Executors.newFixedThreadPool(4);
	        List<Future<Boolean>> set = new ArrayList<Future<Boolean>>(4);

			System.out.println(String.format("Queue Before %s - %s", queue.size() ,queue));
			set.add(executor.submit(getCallable(queue, 2, 1)));
			set.add(executor.submit(getCallable(queue, 3, 2)));
			set.add(executor.submit(getCallable(queue, 5, 3)));
			set.add(executor.submit(getCallable(queue, 7, 4)));
			for (Future<Boolean> ft : set) {
	        	while(!ft.get()) {
					System.out.println("Still executing");
	        	}
	        }
			System.out.println(String.format("Queue After %s - %s", queue.size() ,queue));
			executor.shutdown();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return numbers;
	}
	
	public Callable<Boolean> getCallable(Queue<Integer> queue, Integer divisor, Integer i) {
		return new Callable<Boolean>() {
			@Override
			public Boolean call() {
				int ctr = 0;
				for(Integer number : queue) {
					if(number%divisor == 0) {
						++ctr;
//						System.out.println(String.format("Removed %s by Thread %s", number, i));
						queue.remove(number);
					}
				}
				System.out.println(String.format("Thread %s ran %s times", i, ctr));
				return true;
			}
		};
	}
	
	@Test
	public void testProcessTask() {
		List<Integer> words = processTask(100000);
	}

}
