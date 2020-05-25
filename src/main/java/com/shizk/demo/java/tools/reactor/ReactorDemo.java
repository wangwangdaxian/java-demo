package com.shizk.demo.java.tools.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class ReactorDemo {
    public static void main(String[] args) {
        Flux<Map.Entry<String, Integer>> entryFlux = Flux.fromIterable(new HashMap<String, Integer>().entrySet());
        entryFlux.sort(Comparator.comparingLong(Map.Entry::getValue))
                 .subscribe(
                         System.out::println,
                         error -> System.err.println("Error " + error),
                         () -> System.out.println("Completed"));
    }

    public static void test0() {
        Flux<String>  seq1                   = Flux.just("foo", "bar", "foobar");
        List<String>  iterable               = Arrays.asList("foo", "bar", "foobar");
        Flux<String>  seq2                   = Flux.fromIterable(iterable);
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
        Mono<String>  noData                 = Mono.empty();
        Mono<String>  data                   = Mono.just("foo");
    }

    public static void test1() {
        Flux<Integer> ints = Flux.range(1, 4)
                                 .map(i -> {
                                     if (i <= 3) {
                                         return i;
                                     }
                                     throw new RuntimeException("Got to 4");
                                 });
        ints.subscribe(
                System.out::println,
                error -> System.err.println("Error: " + error));
    }

    public static void test2() {
        Flux<Integer> ints = Flux.range(1, 4);
        ints.subscribe(
                System.out::println,
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"));
    }

    public static void test3() {
        SampleSubscriber<Integer> ss   = new SampleSubscriber<>();
        Flux<Integer>             ints = Flux.range(1, 4);
        ints.subscribe(
                System.out::println,
                error -> System.err.println("Error " + error),
                () -> {System.out.println("Done");},
                s -> s.request(10));
        ints.subscribe(ss);
    }
}
