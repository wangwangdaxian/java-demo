package com.shizk.demo.java.tools.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;

public class AviatorDemo {

    public void test1() {
        final HashMap<String, Object> env = new HashMap<>();
        env.put("x", 5);
        env.put("b", "B");
        final Object r1 = AviatorEvaluator.execute("x<=4", env);
        System.out.println(r1);
        final Object r2 = AviatorEvaluator.execute("b=='B'", env);
        System.out.println(r2);
        final Object r3 = AviatorEvaluator.execute("b=='B'||!(x!=5)", env);
        System.out.println(r3);

        final String condition = "b=='B'&&x==5";
        final Expression expression = AviatorEvaluator.compile(condition, true);
        System.out.println(expression.execute(env));
        AviatorEvaluator.invalidateCache(condition);
    }

    public void test2() {
        final HashMap<String, Object> env = new HashMap<>();
        env.put("city", "shanghai");
        final Object r1 = AviatorEvaluator.execute("include(seq.set('shanghai','beijing'),city)", env);
        System.out.println(r1);
    }

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", 1);
        HashMap<String, Object> env = new HashMap<>();
        env.put("personMap", map);
        Object r = AviatorEvaluator.execute("seq.contains_key(personMap,'name') && seq.contains_key(personMap,'age')",
                                            env);
        Object o2 = AviatorEvaluator.execute(
                "seq.every(seq.list('name'),lambda (x) -> seq.contains_key(personMap,x) end)",
                env);
        System.out.println(r);
        System.out.println(o2);
    }
}
