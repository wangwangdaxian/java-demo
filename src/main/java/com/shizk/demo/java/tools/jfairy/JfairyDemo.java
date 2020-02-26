package com.shizk.demo.java.tools.jfairy;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.company.Company;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;

import java.util.Locale;

public class JfairyDemo {

    public static void test1() {
        Fairy fairy = Fairy.create(Locale.CHINA);
        Company company = fairy.company();
        Person person = fairy.person(PersonProperties.withCompany(company));
        System.out.println(person.getCompany().getName() + company.getName());
    }

    public static void main(String[] args) {
        test1();
    }
}
