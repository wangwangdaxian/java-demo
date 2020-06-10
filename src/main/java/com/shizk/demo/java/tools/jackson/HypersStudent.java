package com.shizk.demo.java.tools.jackson;

import lombok.*;

@Value
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class HypersStudent implements Student {
    String id;
    String name;
    int    age;
    String company;
}
