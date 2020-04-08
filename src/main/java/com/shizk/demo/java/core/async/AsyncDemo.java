package com.shizk.demo.java.core.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务开始");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务结束");
            return "hello";
        });
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("主动设置 cf 任务结果");
            completableFuture.complete("任务通知");
        });
        // 由于 cf 未执行结束，将会被阻塞。5 秒后，另外一个线程主动设置任务结果
        System.out.println("get:" + completableFuture.get());
        // 等待 cf 任务执行结束
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 由于已经设置任务结果，cf 执行结束任务结果将会被抛弃
        System.out.println("get:" + completableFuture.get());
    }
}
