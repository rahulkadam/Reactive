package com.rx.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuterServiceExample {

    public  static void main(String args[]) {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Runnable t1 = new Task("Task1");
        Runnable t2 = new Task("Task2");
        Runnable t3 = new Task("Task3");
        Runnable t4 = new Task("Task4");
        Runnable t5 = new Task("Task5");
        Runnable t6 = new Task("Task6");
        service.execute(t1);
        service.execute(t2);
        service.execute(t3);
        service.execute(t4);
        service.execute(t5);
        service.execute(t6);
        service.shutdown();
    }
}
