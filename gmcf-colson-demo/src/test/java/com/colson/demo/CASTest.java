package com.colson.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author song
 * @description: TODO
 * @date 2021/11/12 下午7:47
 */
public class CASTest {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                    myData.addMyAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //需等待上面20个线程全部计算完成，再用main线程取得最终结果
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\tint type,finally number value:" + myData.number);
        System.out.println(Thread.currentThread().getName() + "\tAtomicInteger type,finally number value:" + myData.atomicInteger);
    }

}

class MyData {
    volatile int number = 0;

    //此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public void addPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomic() {
        atomicInteger.getAndIncrement();
    }
}
