package com.shizk.demo.java.tools.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(value = {"ignore"}, ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Person {
    @JsonProperty("firstName")
    public String _first_name;
    @JsonIgnore
    public int age;

    public Person() {
    }

    @JsonCreator
    public Person(String _first_name) {
        this._first_name = _first_name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "_first_name='" + _first_name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) throws JsonProcessingException {
        final Person person = new Person();
        person._first_name = "abc";
        person.age = 10;
        final ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(person));
        final Person person1 = mapper.readValue("{ \"firstName\" : \"fluffy\", \"age\" : 13, \"ignore\" : 13 }", Person.class);
        System.out.println(person1);
    }
}
