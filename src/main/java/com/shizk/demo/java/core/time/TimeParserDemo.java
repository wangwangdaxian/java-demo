package com.shizk.demo.java.core.time;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class TimeParserDemo {

    public static void main(String[] args) {
        String fmt1 = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS";
        String fmt2 = "yyyy-MM-dd HH:mm:ss.SSSSSSSSS xxxxx";

        String t1 = "2019-10-10 12:20:30.123123123";
        String t2 = "2019-10-10 12:20:30.123";
        String t3 = "2019-10-10 12:20:30.123123123 +00:00";
        String t4 = "2019-10-10 12:20:30.123 +00:00";

//        parse(t1,fmt1);
//        parse(t2,fmt1);
        parse(t3, fmt2);
//        parse(t4,fmt2);
//        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
//                .appendPattern("yyyy-MM-dd HH:mm:ss")
//                .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true)
//                .toFormatter();

//        System.out.println(formatter.toString());
//        parse2(t3);
//        parse2(t4);
//        parse2(t1);
    }

    public static void parse(String time, String fmt) {
        DateTimeFormatter fmtter = DateTimeFormatter.ofPattern(fmt);
//        System.out.println(LocalDateTime.parse(time, fmtter));
        TemporalAccessor ta = fmtter.parse(time);
        System.out.println(ZonedDateTime.from(ta));
        System.out.println(Timestamp.from(Instant.from(ta)));
//        System.out.println(Timestamp.from(Instant.from(ta)).toString());
    }

    public static void parse2(String time) {
        String s = time.replaceFirst(" ", "T").replace(" ", "");
        ZonedDateTime dateTime = ZonedDateTime.parse(s);
        Instant i = dateTime.toInstant();
        System.out.println(i);
        System.out.println(Timestamp.from(i));
        System.out.println(Timestamp.valueOf(dateTime.toLocalDateTime()));
    }

}
