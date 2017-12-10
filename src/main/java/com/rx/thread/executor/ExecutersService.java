package com.rx.thread.executor;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExecutersService {

    public AtomicBoolean shouldExecute;
    public ConcurrentLinkedQueue<Runnable> taskList;
    public List<Thread> threads;
    public int totalThread;

    public ExecutersService() {
       initiateExecuterService(true, 3);
    }

    private void initiateExecuterService(boolean execute, int totalThread) {
        this.shouldExecute = new AtomicBoolean();
        this.shouldExecute.set(execute);
        this.taskList = new ConcurrentLinkedQueue<>();
        this.totalThread = totalThread;
        threads = new LinkedList<>();
        for(int i =0; i < totalThread ; i++) {
            Thread thread = new WorkerThread("Thread:"+i ,  taskList , shouldExecute);
            thread.start();
            threads.add(thread);
        }
    }

    public void execute(Runnable task) {
       // System.out.println("adding task");
        taskList.add(task);
    }

    public void waitTillEnd() {

        boolean flag = true;
        while(flag) {
            for ( Thread t : threads) {
                flag = true;
                if(!t.isAlive()) {
                    flag = false;
                }
            }
            if(!flag) {
                return;
            }
            flag = true;
        }
    }


}
