package com.rx.thread.executor;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class WorkerThread extends Thread {
    public String name;
    public ConcurrentLinkedQueue<Runnable> taskQueue;
    public AtomicBoolean shouldExecute;
    public Object lock = new Object();

    public WorkerThread(String name, ConcurrentLinkedQueue<Runnable> queue, AtomicBoolean execute) {
        this.name = name;
        this.taskQueue = queue;
        this.shouldExecute = execute;
    }

    @Override
    public void run() {

        try {
            while (true) {
//                System.out.println("Starting :" + name);
                Runnable runnable;
                // Poll a runnable from the queue and execute it
                while ((runnable = taskQueue.poll()) != null) {
//                    System.out.println("Executing :" + name);
                    runnable.run();
                }
  //              System.out.println("Finished :" + name);
//                Thread.sleep(100);
            }
        } catch (Exception e) {
        }
    }

    public boolean checkEmpty() {

        while (taskQueue.isEmpty()) {
            System.out.println("checking task: " + taskQueue.isEmpty());
        }
        return true;
    }
}
