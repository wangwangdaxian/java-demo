package com.shizk.demo.java.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(StreamBenchmark.N)
@Measurement(iterations = 3, time = 2)
public class StreamBenchmark {

    public static final int N = 10_000;

    static List<Integer> sourceList = new ArrayList<>();

    static {
        for (int i = 0; i < N; i++) {
            sourceList.add(i);
        }
    }

    @Benchmark
    public List<Double> vanilla() {
        List<Double> result = new ArrayList<>(sourceList.size() / 2 + 1);
        for (Integer i : sourceList) {
            if (i % 2 == 0) {
                result.add(Math.sqrt(i));
            }
        }
        return result;
    }

    @Benchmark
    public List<Double> stream() {
        return sourceList.stream()
                .filter(i -> i % 2 == 0)
                .map(Math::sqrt)
                .collect(Collectors.toCollection(
                        () -> new ArrayList<>(sourceList.size() / 2 + 1)));
    }
}
