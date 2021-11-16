package com.colson.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author song
 * @description:
 * 1 CAS是什么？---> compareAndSet 简单讲--比较并交换
 * 通过sun.misc.Unsafe（rt.jar自带，大部分为native方法，变量使用volatile关键字修饰）类调用
 * 此命令底层是一条CPU并发原语，功能是判断内存某个位置的值是否为期望值，如果是更新为新的值，这个过程是原子的（不可打断）。
 * 调用Unsafe类中的CAS方法，JVM会帮我们实现出CAS汇编指令。这是一种完全依赖于硬件的功能，通过它实现原子操作。
 * 原语的执行必须是连续的，在执行过程中不允许被中断，也就是说CAS是一条CPU的原子指令，不会造成所谓的数据不一致。
 * 2 原理：Unsafe类+CAS思想（自旋）
 * 3 优点：-不用加锁（synchronize） -可以多线程并发执行
 * 4 缺点：-循环时间长开销很大 -只能保证一个共享变量的原子操作 -引出来ABA问题
 *
 * 重点--->ABA问题(有t1/t2俩个线程同时操作主物理内存中的变量A，俩个线程分别拷贝一份A回来，
 * 首先t1把A修改为B，接着t1又修改回A，这时t2过来发现值为A可以进行修改，但是t1已经修改过这个变量，
 * 但t2完全不知道，过程是有问题的)
 * ABA如何解决？新增版本号（类似时间戳），参考ABADemo
 * @date 2021/11/15 下午3:14
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);//主物理内存初始值为5

        //查看主物理内存中的值是不是5，是5的话进行交换，更新主物理内存中的值
        System.out.println(atomicInteger.compareAndSet(5, 2021)+"\t current data:" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t current data:" + atomicInteger.get());
    }
}
