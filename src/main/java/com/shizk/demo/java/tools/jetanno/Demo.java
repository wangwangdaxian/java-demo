package com.shizk.demo.java.tools.jetanno;


import org.intellij.lang.annotations.MagicConstant;

public class Demo {
    @MagicConstant(stringValues = {"x", "v"})
    static String abc;

    public static void main(String[] args) {
        Demo.abc = "x";
        Demo.abc = "y";
        System.out.println(Demo.abc);
    }
}
