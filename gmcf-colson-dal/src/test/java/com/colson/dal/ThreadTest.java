package com.colson.dal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程实现有序执行
 */
public class ThreadTest {

    public static void main(String[] arg) throws InterruptedException {

//        for (int i=0;i<1000;i++) {
//            Thread thread1 = new Thread(() -> System.out.println("111111"));
//            Thread thread2 = new Thread(() -> System.out.println("2222222"));
//            Thread thread3 = new Thread(() -> System.out.println("33333333"));
//            Thread thread4 = new Thread(() -> System.out.println("444444444"));
//
//            thread1.start();
//            thread1.join();
//            thread2.start();
//            thread2.join();
//            thread3.start();
//            thread3.join();
//            thread4.start();
//            thread4.join();
//        }
        System.out.println("-----------------------我是分割线----------------------");
        int index = 0;
        Thread thread = new Thread(() -> {
            int i = index;
            System.out.println(i++);
        });

        for (int i=0;i<100;i++) {
            ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
            System.out.println(index);
            threadExecutor.submit(thread);

            threadExecutor.shutdown();
        }

    }
}
