package com.rx.thread;

public class Task implements Runnable {

    public String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Executing " +this.name);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        System.out.println("Finished " +this.name);
    }
}
