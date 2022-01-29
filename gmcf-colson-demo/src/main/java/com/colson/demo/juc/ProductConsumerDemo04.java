package com.colson.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondition {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase() throws Exception {
        lock.lock();
        try {
            //1 判断
            while (number != 0) {
                condition.await();
            }
            //2 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"线程生产一个,库存为:"+number);
            //3 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrease() throws Exception {
        lock.lock();
        try {
            //1 判断
            while (number == 0) {
                condition.await();
            }
            //2 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"线程消费一个,库存为:"+number);
            //3 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

//    public synchronized void increase() throws Exception {
//        1 判断
//        while (number != 0) {
//            wait();
//        }
//        2 干活
//        number++;
//        System.out.println(Thread.currentThread().getName()+"线程生产一个,库存为:"+number);
//        3 通知
//        notifyAll();
//    }

//    public synchronized void decrease() throws Exception {
//        while (number == 0) {
//            wait();
//        }
//        number--;
//        System.out.println(Thread.currentThread().getName()+"线程消费一个,库存为:"+number);
//        notifyAll();
//    }
}

/**
 * 题目:现在俩个线程,可以操作初始值为0的一个变量,
 * 实现一个线程对该变量加1,一个线程对该变量减1,
 * 实现交替,来10轮,变量依旧为0.
 *
 * 1.高内聚低耦合前提下,多线程操作资源类
 * 2.判断/干活/通知
 * 3.防止虚假唤醒,判断不能用if,必须用while
 *
 * 知识小总结 = 多线程编程套路 + while判断 + 新版写法
 * 老版 = synchronized + wait() + notifyAll()
 * 新版 = Lock + condition.await() + condition.signalAll()
 */
public class ProductConsumerDemo04 {

    public static void main(String[] args) {

        AirCondition airCondition = new AirCondition();

        //四个线程,俩个生产 俩个消费
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrease();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
