package com.shizk.demo.java.tools.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisStringReactiveCommands;

import java.util.concurrent.TimeUnit;

public class LettuceDemo {
    public static void main(String[] args) throws InterruptedException {
        RedisClient client = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connection = client.connect();
//        RedisCommands<String, String> syncCommands = connection.sync();
//        syncCommands.set("key", "Hello, Redis!");

//        RedisAsyncCommands<String, String> commands = connection.async();
//        RedisFuture<String> future = commands.get("key");
//        future.thenAccept(System.out::println);

        RedisStringReactiveCommands<String, String> commands = connection.reactive();
        commands
                .get("key")
                .subscribe(System.out::println);


        System.out.println("main");
        TimeUnit.SECONDS.sleep(3);
        connection.close();
        client.shutdown();
    }
}
