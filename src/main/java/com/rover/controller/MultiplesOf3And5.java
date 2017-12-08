package com.rover.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.assertj.core.util.Lists;
import org.junit.Test;

public class MultiplesOf3And5 {

	public Long processTask(Integer range) {
		Long finalSum = 0L;

		try {
			List<Integer> divisors = Lists.newArrayList(3,5);
			int taskSize = 200;
			int partitions = range / taskSize;
			if(range % taskSize > 0) {
				partitions++;
			}
	        final ExecutorService executor = Executors.newFixedThreadPool(partitions);
	        List<Future<Long>> set = new ArrayList<Future<Long>>(partitions);

	        for(int i = 0; i<partitions;i++) {
				set.add(executor.submit(getCallable((taskSize*i)+1, range < taskSize*(i+1)? range : taskSize*(i+1), divisors, (i+1))));
	        }
			for (Future<Long> ft : set) {
	        	finalSum += ft.get();
	        }
			System.out.println(String.format("Final sum is %s", finalSum));
			executor.shutdown();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return finalSum;
	}
	
	public Callable<Long> getCallable(Integer start, Integer end, List<Integer> divisors, Integer threadId) {
		return new Callable<Long>() {
			@Override
			public Long call() {
				Long sum = 0L;
				for(int i = start; i <= end; i++) {
					for(Integer divisor : divisors) {
						if(i % divisor == 0) {
//							System.out.println(String.format("Adding %s to %s", i, sum));
							sum += i;
							break;
						}
					}
				}
				System.out.println(String.format("Thread %s ran from [%s to %s] completed with sum of %s", threadId, start, end, sum));
				return sum;
			}
		};
	}
	
	@Test
	public void testProcessTask() {
		Long sum = processTask(999);
	}

}
