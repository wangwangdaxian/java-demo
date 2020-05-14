package com.shizk.demo.java.tools.owner;

import org.aeonbits.owner.Config;

public interface BaseBean extends Config {

    @Key("base.prefix")
    @DefaultValue("x")
    String basePrefix();

    @Key("${base.prefix}.age")
    int age();

}
