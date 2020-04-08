package com.shizk.demo.java.core.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class FileChannelDemo {
    public static void main(String[] args) throws FileNotFoundException {
        //1.创建一个RandomAccessFile（随机访问文件）对象，
        RandomAccessFile raf = new RandomAccessFile("/usr/local/hbase/logs/hbase-shizk233-master-shizk233-mbp.local.out.4", "rw");

    }
}
