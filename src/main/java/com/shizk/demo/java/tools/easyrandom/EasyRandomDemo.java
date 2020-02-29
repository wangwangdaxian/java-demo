package com.shizk.demo.java.tools.easyrandom;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.nio.charset.StandardCharsets;

public class EasyRandomDemo {

    public static void main(String[] args) {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(1)
                .charset(StandardCharsets.UTF_8)
                .stringLengthRange(5, 6);
        EasyRandom random = new EasyRandom(parameters);
        Person person = random.nextObject(Person.class);
        System.out.println(person);
    }
}
