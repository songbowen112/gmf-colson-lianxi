package com.colson.dal.demo.juc;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {

    private Integer flag = 0;//标志位

    private Lock lock = new ReentrantLock();//锁
    private Condition c1 = lock.newCondition();//钥匙1
    private Condition c2 = lock.newCondition();//钥匙2
    private Condition c3 = lock.newCondition();//钥匙3
    private Condition current = null;//当前condition
    private Condition next = null;//下个condition

    public void print() throws Exception {
        String threadName = Thread.currentThread().getName();

        if (StringUtils.isNotBlank(threadName)) {
            if ("A".equals(threadName)) {
                current = c1;
                next = c2;
                flag = 5;
            } else if ("B".equals(threadName)) {
                current = c2;
                next = c3;
                flag = 10;
            } else if ("C".equals(threadName)) {
                current = c3;
                next = c1;
                flag = 15;
            }
        }

        lock.lock();
        while (flag != 5) {
            current.await();
        }
        try {
            for (int i = 0; i < flag; i++) {
                System.out.println(threadName + "     " + (i + 1));
            }
            flag = 10;
            next.signal();
        } finally {
            lock.unlock();
        }


//        try {
//            while (flag != 0) {
//                for (int i = 0; i < 5; i++) {
//                    System.out.println(threadName + "     " + (i + 1));
//                }
//                flag = 1;
//                c2.signal();
//            }
//            while (flag != 1) {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println(threadName + "     " + (i + 1));
//                }
//                flag = 2;
//                c3.signal();
//            }
//            while (flag != 2) {
//                for (int i = 0; i < 15; i++) {
//                    System.out.println(threadName + "     " + (i + 1));
//                }
//                flag = 0;
//                c1.signal();
//            }
//        } finally {
//            lock.unlock();
//        }
    }
}

//    public void printA() throws Exception {
//        lock.lock();
//        try {
//            //1 判断
//            while (flag != 0) {
//                c1.await();
//            }
//            //2 干活
//            for (int i = 0; i < 5; i++) {
//                System.out.println(threadName + "     " + (i + 1));
//            }
//            //3 通知
//            //注意要先改标志位
//            flag = 1;
//            c2.signal();
//        } finally {
//            lock.unlock();
//        }
//    }
//    public void printB() throws Exception {
//        lock.lock();
//        try {
//            while (flag != 1) {
//                c2.await();
//            }
//            for (int i = 0; i < 10; i++) {
//                System.out.println(threadName + "     " + (i + 1));
//            }
//            flag = 2;
//            c3.signal();
//        } finally {
//            lock.unlock();
//        }
//    }
//    public void printC() throws Exception {
//        lock.lock();
//        try {
//            while (flag != 2) {
//                c3.await();
//            }
//            for (int i = 0; i < 15; i++) {
//                System.out.println(threadName + "     " + (i + 1));
//            }
//            flag = 0;
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
                    shareData.print();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.print();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.print();
                    System.out.println("--------------------------------");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }, "C").start();
    }
}
