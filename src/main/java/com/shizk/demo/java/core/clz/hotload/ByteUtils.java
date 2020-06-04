package com.shizk.demo.java.core.clz.hotload;

public class ByteUtils {

    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff;
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = ((byte) ((value >> 8 * i) & 0xff));
        }
        return b;
    }

    public static String bytes2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    public static byte[] string2Bytes(String str) {
        return str.getBytes();
    }

    public static byte[] bytesReplace(byte[] original, int offset, int len, byte[] replace) {
        byte[] newBytes = new byte[original.length + (replace.length - len)];
        System.arraycopy(original, 0, newBytes, 0, offset);
        System.arraycopy(replace, 0, newBytes, offset, replace.length);
        System.arraycopy(original, offset + len, newBytes, offset + replace.length, original.length - offset - len);
        return newBytes;
    }
}
