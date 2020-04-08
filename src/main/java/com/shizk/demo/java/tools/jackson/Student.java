package com.shizk.demo.java.tools.jackson;

public class Student {
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    private String name;
    private int age;
    private String company;

//    public String getSex() {
//        return sex;
//    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private String sex;

    public Student() {
    }

//    public String getName() {
//        return name;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    static class Builder {
        int a;
        String x = "x";
    }

}


