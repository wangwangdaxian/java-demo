package com.shizk.demo.java.tools.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.HashMap;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TargetClass {
    @JsonProperty("name")
    private String table;
    private int age;


    public static void main(String[] args) {
        final SourceType type = new SourceType();
        type.setName("jack");
        type.setAge(20);
        type.setIgnore("ign");
        final ObjectMapper mapper = new ObjectMapper();
        final TargetClass convertValue = mapper.convertValue(type, TargetClass.class);
        System.out.println(convertValue);

        final HashMap<String, String> map = new HashMap<>();
        map.put("name", "jack2");
        map.put("age", "22");
        final TargetClass targetClass2 = mapper.convertValue(map, TargetClass.class);
        System.out.println(targetClass2);
    }
}
