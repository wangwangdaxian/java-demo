package com.shizk.demo.java.tools.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDemo {

    public static void test() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String jsonString = "{\"brand\":\"abc\",\"name\":\"Mahesh\"}";
        JsonNode tree = mapper.readTree(jsonString);
        JsonNode brand = tree.findValue("brand");
        System.out.println(brand.asText());
        //        Student student = mapper.treeToValue(tree, Student.class);
        //        System.out.println(student);
        System.out.println(tree.path("name"));
    }

    public static void test2() throws JsonProcessingException {
        Student student = HypersStudent.builder().id("a").age(1).name("a").company("a").build();
        //        Student student = Student.builder().setAge(10).setName("a").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(student);
        System.out.println(s);

        Student student1 = objectMapper.readValue(s, Student.class);
        System.out.println(student1);
    }

    public static void main(String[] args) throws JsonProcessingException {
        test2();
    }
}
