package com.rx.thread.producerconsumer;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer {
    public AtomicInteger current;
    public ConcurrentLinkedQueue<Integer> queue;
    public Object lock;

    public Consumer(AtomicInteger current, ConcurrentLinkedQueue queue , Object lock) {
        this.current = current;
        this.queue = queue;
        this.lock = lock;
    }

    public void consume() throws InterruptedException {
        int i = 0;
        while (true) {
            synchronized (lock) {
                System.out.println("in Consumer");
                while (queue.isEmpty()) {
                    lock.wait();
                }
                System.out.println("Consume" +queue.poll());
                i++;
                lock.notify();
                Thread.sleep(2000);
            }
        }
    }
}
