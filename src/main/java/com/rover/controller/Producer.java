package com.rover.controller;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    private Integer count;
 
    public Producer(BlockingQueue<Integer> queue, Integer count) {
        this.queue = queue;
        this.count = count;
    }
 
    public void run() {
        try {
            for (int i = 1; i <= count; i++) {
                queue.put(produce(i));
                Thread.sleep(500);
            }
            queue.put(-1);  // indicates end of producing
            System.out.println("Producer STOPPED.");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
 
    private Integer produce(int i) {
        Integer number = new Integer(i);
        System.out.println("Producing number => " + number);
        return number;
    }
}
