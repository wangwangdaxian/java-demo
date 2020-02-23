package com.shizk.demo.java.tools.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonDemo {
    public static void main(String[] args) throws JsonProcessingException {
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
}
