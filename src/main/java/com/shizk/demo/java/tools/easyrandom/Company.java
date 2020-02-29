package com.shizk.demo.java.tools.easyrandom;

import lombok.Data;

@Data
public class Company {
    private String companyName;
    private long companyId;

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
