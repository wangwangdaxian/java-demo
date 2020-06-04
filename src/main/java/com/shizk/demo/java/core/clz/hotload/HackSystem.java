package com.shizk.demo.java.core.clz.hotload;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * 为JavaClass劫持java.lang.System提供支持
 * 除了out和err之外，其余都转发给System处理
 */
public class HackSystem {

    public final static InputStream           in     = System.in;
    public static       ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    public final static PrintStream           out    = new PrintStream(buffer);
    public final static PrintStream           err    = out;


    public static String getBufferString() {
        return buffer.toString();
    }

    public static void clearBuffer() {
        buffer.reset();
    }

    //还需要抄录System的其他方法，转发System代理实现
}
