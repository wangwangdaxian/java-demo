package com.shizk.demo.java.core.spi.impl;

import com.shizk.demo.java.core.spi.PayService;

public class AliPay implements PayService {
    @Override
    public void pay() {
        System.out.println("Ali Pay");
    }
}
