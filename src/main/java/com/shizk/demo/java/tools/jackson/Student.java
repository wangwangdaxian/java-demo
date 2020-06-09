package com.shizk.demo.java.tools.jackson;

import lombok.*;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class Student {
    String name;
    int    age;
    String company;
}


