package com.rx.thread;


import com.rx.service.DataStore;

public class MainThreadExample {

    public static void main(String args[]) {
        System.out.println("Thread Starting");
        CollegeThread c = new CollegeThread();
        c.start();
        StudentThread st = new StudentThread();
        Thread th = new Thread(st);
        th.start();
        try {
            c.join();
            th.join();
        } catch (Exception e) {

        }
        Thread thread = new Thread(() -> {
            System.out.println("Executing runnable functional Interface");
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {

        }
        System.out.println("finished");
    }
}
