package com.shizk.demo.java.core.concrurrent.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {
    private static final int  CORE_POOL_SIZE  = 5;
    private static final int  MAX_POOL_SIZE   = 10;
    private static final int  QUEUE_CAPACITY  = 7;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                                                             MAX_POOL_SIZE,
                                                             KEEP_ALIVE_TIME,
                                                             TimeUnit.SECONDS,
                                                             new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                                                             new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i);
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
        Thread.currentThread().notifyAll();
    }
}
