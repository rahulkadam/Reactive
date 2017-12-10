package com.rx.thread;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPool {
    public List<Thread> workerThreadList;
    public BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>();
    public int totalThread;
    public Object lock = new Object();

    public ThreadPool(int n) {
      this.totalThread = n;
    }

    public void init() {

    }




}
