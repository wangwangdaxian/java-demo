package com.shizk.demo.java.jmh;

import org.openjdk.jmh.annotations.*;
import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 3)
@Measurement(iterations = 3, time = 2)
@Threads(1)
public class ReactorBenchmark {
    public static final int N = 10_000_000;

    @Benchmark
    public void stream() {
        IntStream.range(0, N)
                 .asDoubleStream()
                 .map(Math::sqrt)
                 .filter(value -> value > Math.log10(N))
                 .sorted()
                 .forEach(value -> {});
    }


    @Benchmark
    public void reactor() {
        Flux.range(0, N)
            .map(Math::sqrt)
            .filter(value -> value > Math.log10(N))
            .sort()
            .subscribe();
    }
}
