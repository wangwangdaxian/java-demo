package com.shizk.demo.java.tools.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisDemo {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis("localhost");
//        jedis.set("foo", "bar");
//        String value = jedis.get("foo");
//        System.out.println(value);

        //1.common-pool2 连接池配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        //可以根据需要设置相关属性
        poolConfig.setMaxTotal(200);
        poolConfig.setMaxIdle(50);
        poolConfig.setMinIdle(10);
        poolConfig.setMaxWaitMillis(200);

        //2.初始化Jedis连接池
        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1");
        Jedis jedis = jedisPool.getResource();
        jedis.set("foo", "abc");
        String value = jedis.get("foo");
        System.out.println(value);
        jedis.echo("");
        jedis.ping();
        jedis.close();
    }
}
