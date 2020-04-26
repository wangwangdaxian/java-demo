package com.shizk.demo.java.tools.owner;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:owner.properties")
public interface OwnerBean extends Config {

    String name();

    @Key("x.age")
    int age();

    @DefaultValue("hypers")
    String company();
}
