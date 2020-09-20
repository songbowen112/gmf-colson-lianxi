package com.colson.dal.demo.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("*************come in call()");
        return 1024;
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println("*************come in run()");
    }
}

/**
 * ------------------------使用Callable和Future创建线程---------------------
 * 和Runnable接口不一样，Callable接口提供了一个call（）方法作为线程执行体，call()方法比run()方法功能要强大。
 * 》call()方法可以有返回值
 * 》call()方法可以声明抛出异常
 *
 * Java5提供了Future接口来代表Callable接口里call()方法的返回值，并且为Future接口提供了一个实现类FutureTask，
 * 这个实现类既实现了Future接口，还实现了Runnable接口，因此可以作为Thread类的target。
 * 在Future接口里定义了几个公共方法来控制它关联的Callable任务。
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //匿名内部类+Lamda表达式启动线程
        new Thread(new FutureTask<Integer>(() -> {
            System.out.println("*************come in call()");
            return 1024;})).start();

        //匿名内部类启动线程
        new Thread(new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("*************come in call()");
                return 1024;
            }
        })).start();

        //普通方式启动线程
        /*FutureTask<Integer> futureTask = new FutureTask(new MyThread1());
        Thread t1 = new Thread(futureTask);
        t1.start();
        System.out.println(futureTask.get());*/

        MyThread2 t2 = new MyThread2();
        t2.start();
    }

}
