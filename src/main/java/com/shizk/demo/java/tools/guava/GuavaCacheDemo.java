package com.shizk.demo.java.tools.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("NullableProblems")
public class GuavaCacheDemo {

    public static final Map<String, Integer> map = new HashMap<>(10);

    static {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("f", 6);
        map.put("g", 7);
        map.put("h", 8);
    }

    public static void main(String[] args) {

        LoadingCache<String, Integer> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .refreshAfterWrite(Duration.ZERO)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return map.get(key);
                    }
                });
    }
}
