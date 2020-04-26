package com.shizk.demo.java.tools.owner;

import org.aeonbits.owner.ConfigFactory;

public class OwnerDemo {
    public static void main(String[] args) {
        final OwnerBean cfg = ConfigFactory.create(OwnerBean.class);
        System.out.println(cfg.name() + cfg.age() + cfg.company());
    }
}
