package com.shizk.demo.java.core.clz.dynamiccompile;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.Callable;

public class DynamicCompile {

    public static Object compileInvoke(String code, String classFullName) throws Throwable {
        String root    = DynamicCompile.class.getClassLoader().getResource("").getPath();
        String jarFile = root.replace("/classes", "/lib");

        ClassGenerator builder  = new ClassGenerator(root);
        Class<?>       cls      = builder.generate(classFullName, code, jarFile);
        Object         instance = cls.newInstance();
        if (!(instance instanceof Callable)) {
            throw new RuntimeException("only support Callable");
        }

        MethodType   methodType   = MethodType.methodType(Object.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(cls, "call", methodType);
        return methodHandle.invoke(instance);
    }
}
