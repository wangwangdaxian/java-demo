package com.shizk.demo.java.tools.validation;

import static com.redfin.validity.Validity.validate;

public class ValidationDemo {
    public static void main(String[] args) {
        String abc = validate()
                .that("abc")
                .isNotEmpty();
        System.out.println(abc);
    }
}
