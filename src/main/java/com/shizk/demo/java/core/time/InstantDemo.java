package com.shizk.demo.java.core.time;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class InstantDemo {


    public static void test1() {
//        System.out.println(new Date().getTime());
//        System.out.println(System.currentTimeMillis());
//        System.out.println(Instant.now().toEpochMilli());
//        System.out.println(Instant.now().getEpochSecond());
        System.out.println(Instant.ofEpochSecond(1586169894).toString());
//        System.out.println(Instant.ofEpochSecond(ZonedDateTime.parse("2020-03-19T00:00:00+08:00").toInstant().getEpochSecond()));
//        System.out.println(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("+08:00")));
//        final Instant start = Instant.now();
//        final Instant end = start.minus(15, ChronoUnit.MINUTES);
//        System.out.println(start.toEpochMilli()-end.toEpochMilli());
    }

    public static void test2() {
        long l = TimeUnit.SECONDS.toMillis(5);
        System.out.println(l);
    }


    public static void main(String[] args) {
        test1();
    }
}
