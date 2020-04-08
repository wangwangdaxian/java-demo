package com.shizk.demo.java.tools.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;

public class AviatorDemo {
    public static void main(String[] args) {
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
}
