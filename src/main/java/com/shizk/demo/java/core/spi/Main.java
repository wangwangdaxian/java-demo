package com.shizk.demo.java.core.spi;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<PayService> services = ServiceLoader.load(PayService.class);
        for (PayService service : services) {
            service.pay();
        }
    }
}
