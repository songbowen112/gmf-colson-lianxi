package com.colson.dal.demo.juc;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {

    private int number = 1;//标志位 A-1 B-2 C-3

    private Lock lock = new ReentrantLock();//锁
    private Condition c1 = lock.newCondition();//钥匙1
    private Condition c2 = lock.newCondition();//钥匙2
    private Condition c3 = lock.newCondition();//钥匙3

    public void print(int num) {
        int num1 = num / 5;
        lock.lock();

        try {
            // 判断
            while (number != num1) {
                if (num == 5) {
                    c1.await();
                } else if (num == 10) {
                    c2.await();
                } else if (num == 15) {
                    c3.await();
                } else {
                    System.out.println("结束");
                    return;
                }
            }
            // 干活
            for (int j = 1; j <= num; j++) {
                System.out.println(Thread.currentThread().getName() + "\t" + j);
            }

            // 通知
            if (num == 5) {
                number = 2;
                c2.signal();
            } else if (num == 10) {
                number = 3;
                c3.signal();
            } else {
                number = 1;
                c1.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

//    public void printA() throws Exception {
//        lock.lock();
//        try {
//            //1 判断
//            while (flag != 1) {
//                c1.await();
//            }
//            //2 干活
//            for (int i = 0; i < 5; i++) {
//                System.out.println(threadName + "     " + (i + 1));
//            }
//            //3 通知
//            //注意要先改标志位
//            flag = 2;
//            c2.signal();
//        } finally {
//            lock.unlock();
//        }
//    }
//    public void printB() throws Exception {
//        lock.lock();
//        try {
//            while (flag != 2) {
//                c2.await();
//            }
//            for (int i = 0; i < 10; i++) {
//                System.out.println(threadName + "     " + (i + 1));
//            }
//            flag = 3;
//            c3.signal();
//        } finally {
//            lock.unlock();
//        }
//    }
//    public void printC() throws Exception {
//        lock.lock();
//        try {
//            while (flag != 3) {
//                c3.await();
//            }
//            for (int i = 0; i < 15; i++) {
//                System.out.println(threadName + "     " + (i + 1));
//            }
//            flag = 1;
//            c1.signal();
//        } finally {
//            lock.unlock();
//        }
//    }

/**
 * 备注：多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下
 * <p>
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 */
public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.print(5);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.print(10);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.print(15);
                    System.out.println("--------------------------------");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }, "C").start();
    }
}
