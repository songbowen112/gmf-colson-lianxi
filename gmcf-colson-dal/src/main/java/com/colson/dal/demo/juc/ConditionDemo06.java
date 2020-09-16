package com.colson.dal.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {

    private Integer flag = 0;//标志位

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printA() throws Exception {
        lock.lock();
        try {
            //1 判断
            while (flag != 0) {
                c1.await();
            }
            //2 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "     " + (i + 1));
            }
            //3 通知
            //注意要先改标志位
            flag = 1;
            c2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB() throws Exception {
        lock.lock();
        try {
            while (flag != 1) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "     " + (i + 1));
            }
            flag = 2;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printC() throws Exception {
        lock.lock();
        try {
            while (flag != 2) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "     " + (i + 1));
            }
            flag = 0;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 备注：多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 */
public class ConditionDemo06 {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.printA();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.printB();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.printC();
                }
                System.out.println("--------------------------------");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
