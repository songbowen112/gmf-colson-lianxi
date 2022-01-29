package com.colson.demo.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类 = 实例变量 + 实例方法
class Ticket{

    private int num = 30;

    Lock lock = new ReentrantLock();//可重用锁

    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName()+"售票员卖出第"+num--+"张票,还剩"+num+"张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 题目:三个售票员卖出 30张票
 * 笔记:如何编写企业级多线程
 *
 * 1.在高内聚低耦合前提下,线程 操作 资源类
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) { //主线程,一切程序的入口
        Ticket ticket = new Ticket();

        //Runnable是一个函数式接口
//        @FunctionalInterface
//        public interface Runnable {
//            public abstract void run();
//        }

        /**
         * Lambda表达式启动线程
         */
        new Thread(() -> { for (int i = 0; i < 30; i++) ticket.sale(); },"A").start();
        new Thread(() -> { for (int i = 0; i < 30; i++) ticket.sale(); },"B").start();
        new Thread(() -> { for (int i = 0; i < 30; i++) ticket.sale(); },"C").start();

        /**
         * 匿名内部类启动线程
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.sale();
//                }
//            }
//        },"A").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.sale();
//                }
//            }
//        },"B").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 30; i++) {
//                    ticket.sale();
//                }
//            }
//        },"C").start();
    }
}

