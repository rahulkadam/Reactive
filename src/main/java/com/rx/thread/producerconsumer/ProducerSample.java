package com.rx.thread.producerconsumer;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ProducerSample {

    public AtomicInteger capacity;
    public ConcurrentLinkedQueue<Integer> buffer;

    public ProducerSample() {
        this.capacity = new AtomicInteger();;
        capacity.set(10);
        buffer = new ConcurrentLinkedQueue<>();
    }

    public static void main(String args[])  throws InterruptedException{
        System.out.println("Starting ");
        ProducerSample p = new ProducerSample();

        Producer producer = new Producer(p.capacity , p.buffer, p);
        Consumer consumer = new Consumer(p.capacity , p.buffer, p);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Executing Thread ");
                    producer.produce();
                } catch (Exception e) {}
            }
        });

        t.start();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Executing Thread ");
                    consumer.consume();
                } catch (Exception e) {}
            }
        });

        t1.start();

        System.out.println("Finished Thread ");
    }

}
