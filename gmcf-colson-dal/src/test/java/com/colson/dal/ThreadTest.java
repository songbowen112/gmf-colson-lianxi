package com.colson.dal;

import java.util.concurrent.*;

/**
 * 多线程实现有序执行
 */
public class ThreadTest {

    public static void main(String[] arg) throws InterruptedException {

        for (int i=0;i<5;i++) {
            Thread thread1 = new Thread(() -> System.out.println("111111"));
            Thread thread2 = new Thread(() -> System.out.println("2222222"));
            Thread thread3 = new Thread(() -> System.out.println("33333333"));
            Thread thread4 = new Thread(() -> System.out.println("444444444"));

            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
            thread3.start();
            thread3.join();
            thread4.start();
            thread4.join();
        }
        System.out.println("-----------------------我是分割线----------------------");

        Thread thread1 = new Thread(() -> {
            System.out.println("111111");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("222222");
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("333333");
        });
        Thread thread4 = new Thread(() -> {
            System.out.println("444444");
        });
        ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
        threadExecutor.submit(thread1);
        threadExecutor.submit(thread2);
        threadExecutor.submit(thread3);
        threadExecutor.submit(thread4);
        threadExecutor.shutdown();

        Thread.sleep(100);
        System.out.println("-----------------------我是分割线----------------------");
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(4);
        queue.add(thread1);
        queue.add(thread2);
        queue.add(thread3);
        queue.add(thread4);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,10,5000,TimeUnit.SECONDS,queue);
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("action...");
            }
        });
        threadPoolExecutor.shutdown();
    }
}
