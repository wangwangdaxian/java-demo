package com.shizk.demo.java.core.clz.hotload;

import java.lang.reflect.Method;

public class JavaclassExecuter {
    /**
     * JavaclassExecute执行外部传过来的代表一个Java类的Byte数组
     * 替换掉java.lang.System的符号引用后，使用HotSwapClassLoader加载生成一个 Class对象，由于每次执行 execute()
     * 方法都会生成一个新的类加载器实例，因此同一个类可以实现重复加载。 然后反射调用这
     * 个 Class对象的 main()方法，如果期间出现任何异常，将异常信息打印到 HackSystem.out
     * 中，最后把缓冲区中的信息作为方法的结果来返回 。
     *
     * @return 执行结果
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier      cm        = new ClassModifier(classByte);
        byte[]             modiBytes = cm.modifyUTF8Constant("java/lang/System", "com/shizk/demo/java/core/clz/hotload/HackSystem");
        HotSwapClassLoader loader    = new HotSwapClassLoader();
        Class<?>           clazz     = loader.loadByte(modiBytes);
        try {
            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, (Object) new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}