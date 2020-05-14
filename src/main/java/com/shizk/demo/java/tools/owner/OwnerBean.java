package com.shizk.demo.java.tools.owner;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${filename}")
public interface OwnerBean extends BaseBean {

    String name();

    @DefaultValue("hypers")
    String company();
}
