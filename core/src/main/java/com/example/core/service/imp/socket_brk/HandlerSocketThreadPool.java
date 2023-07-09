package com.example.core.service.imp.socket_brk;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 线程池处理类
public class HandlerSocketThreadPool {

    // 线程池
    private ExecutorService executor;

    public HandlerSocketThreadPool(int maxPoolSize, int queueSize){

        this.executor = new ThreadPoolExecutor(
                3, // 8
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize) );
    }

    public void execute(Runnable task){
        this.executor.execute(task);
    }
}
