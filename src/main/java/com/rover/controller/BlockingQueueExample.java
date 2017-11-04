package com.rover.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

	public static void main(String args[]) {
		try {
			BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
			int count = 20;
			
			Runnable producer = new Runnable() {
				@Override
				public void run() {
					try {
						for (int i = 1; i <= count; i++) {
							System.out.println("Adding " + i);
							queue.put(i);
							Thread.sleep(100);
						}
						queue.put(-1);
						System.out.println("All numbers Added.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			Runnable consumer = new Runnable() {
				@Override
				public void run() {
					try {
						while(true) {
							Integer i = queue.take();
							System.out.println("Processed " + i);
							if (i == null || i == -1) {
								System.out.println("All numbers processed.");
								break;
							}
							Thread.sleep(500);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			
			(new Thread(producer)).start();
			(new Thread(consumer)).start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
