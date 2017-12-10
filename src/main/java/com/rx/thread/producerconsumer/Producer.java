package com.rx.thread.producerconsumer;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer {
    public AtomicInteger current;
    public ConcurrentLinkedQueue<Integer> queue;
    public Object lock;
    public Producer(AtomicInteger current , ConcurrentLinkedQueue queue ,  Object lock) {
        this.current = current;
        this.queue = queue;
        this.lock = lock;
    }

    public void produce() throws InterruptedException{
        int i=0;
        while(true) {
            synchronized (lock) {
                System.out.println("in Producer");
                while(queue.size() == current.get()) {
                    lock.wait();
                }
                System.out.println("value produced" + i);
                queue.add(i);
                i++;
                lock.notify();
                Thread.sleep(2000);
            }
        }
    }
}
