package com.colson.demo.juc;

import java.util.concurrent.TimeUnit;

class Phone{

    public static synchronized void sendEmail() throws Exception {
        //JUC新枚举TimeUnit,提供各种时间类型
        TimeUnit.SECONDS.sleep(3);
        System.out.println("*****sendEmail");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println("*****sendSMS");
    }

    public void sayHello() throws Exception {
        System.out.println("*****sayHello");
    }
}

/**
 * 8 lock
 * 1.标准访问,请问先打印邮件还是短信---不一定
 * 2.暂停3秒在邮件方法,请问先打印邮件还是短信---邮件
 * 3.新增普通sayHello方法,请问先打印邮件还是sayHello---sayHello
 * 4.俩部手机,请问先打印邮件还是短信---短信
 * 5.俩个静态方法,同一部手机,请问先打印邮件还是短信---邮件
 * 6.俩个静态方法,俩部手机,请问先打印邮件还是短信---邮件
 * 7.一个静态方法,一个普通同步方法,同一部手机,请问先打印邮件还是短信---短信
 * 8.一个静态方法,一个普通同步方法,俩部手机,请问先打印邮件还是短信---短信
 *
 *
 * 1)
 * 一个对象如果有多个synchronized方法,某一时刻内,只要有一个线程去调用其中的一个synchronized方法了,
 * 其他线程就只能等待,换句话说,某一个时刻内,只能有唯一一个线程去访问这些synchronized方法.
 * 2)
 * 锁的是当前对象this,被锁定后,其他的线程都不能进入到当前对象的其他的synchronized方法.
 * 3)
 * 加普通方法后,发现和同步锁无关.
 * 4)
 * 换成俩个对象后,不是同一把锁了,情况立刻变化.
 * 5)
 * synchronized实现同步的基础:Java中的每一个对象可以作为锁.
 * 具体表现为以下三种形式.
 * 对于普通同步方法,锁是当前实例对象,锁的是当前对象this.
 * 对于同步方法块,锁是synchronized括号里配置的对象.
 * 对于静态同步方法,锁的是当前类的Class对象
 * 6)
 * 当一个线程试图访问同步代码块时,他首先必须得到锁,退出或跑出异常时必须释放锁.
 * 也就是说如果一个实例对象的非静态同步方法获得锁后,该实例对象的其他非静态同步方法必须等待释放锁
 * 的方法释放锁后才能获取锁,可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是
 * 不同的锁,所以不需要等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁.
 * 7)
 * 所有的静态同步方法用的也是同一把锁--类对象Class本身,
 * 这俩把锁是俩个不同的对象,所以静态同步方法与非静态同步方法之间是不会有竞态条件的.
 * 但是一旦一个静态同步方法获得锁后,其他的静态同步方法都必须等待该方法释放锁后才能获取锁,
 * 而不管是同一个实例对象的静态同步方法之间,
 * 还是不同的实例对象的静态同步方法之间,只要他们是同一个类的实例对象!
 *
 */
public class Lock8Demo05 {

    public static void main(String[] args) throws InterruptedException {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendSMS();
//                phone.sayHello();
//                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
