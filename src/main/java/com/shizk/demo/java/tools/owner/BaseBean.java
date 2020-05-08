package com.shizk.demo.java.tools.owner;

import org.aeonbits.owner.Config;

public interface BaseBean extends Config {

    @Key("x.age")
    int age();

}
