package com.shizk.demo.java.core.clz.hotload;

/**
 * 为了 多次载入执行类而加入的加载器,把defineClass方法开放出来，
 * 只有外部显式调用的时候才会使用到loadByte方法
 * 由虚拟机调用时，仍然按照原有的双亲委派规则使用 loadClass方法进行类加载
 **/
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class<?> loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }
}
