package com.colson.util;

/**
 * 1，jvm 参数
 * -Xmx4G -Xms4G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * 2，运行完程序执行命令如下
 * 3，jps 查看进程
 * 4，jmap -histo pid(线程ID) 查看实例对象总数
 *
 */
public class MemoryEscape {
    public static void main(String[] args) {
        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            alloc();
        }
        // 查看执行时间
        long a2 = System.currentTimeMillis();
        System.out.println("cost " + (a2 - a1) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }

    private static void alloc() {
        User user = new User();

    }

    static class User {

    }
}