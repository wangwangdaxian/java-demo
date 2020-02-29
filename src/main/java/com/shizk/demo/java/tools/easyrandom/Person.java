package com.shizk.demo.java.tools.easyrandom;

import lombok.Data;

@Data
public class Person {
    private String name;
    private int age;
    private Company company;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", company=" + company +
                '}';
    }
}
