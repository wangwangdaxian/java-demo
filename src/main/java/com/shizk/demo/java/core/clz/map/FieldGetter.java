package com.shizk.demo.java.core.clz.map;

import com.shizk.demo.java.tools.jackson.Person;

import java.lang.reflect.Field;
import java.util.HashMap;

public class FieldGetter {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        final UserBean userBean = new UserBean();
        userBean.setId(2);
        userBean.setName("abc");
        final Field[] fields = UserBean.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        final HashMap<String, Object> env = new HashMap<>();
        for (Field field : fields) {
            env.put(field.getName(), field.get(userBean));
        }
        System.out.println(env);
        final Person person = new Person();
        person.age = 10;
        System.out.println(fields[0].get(person));
    }
}
