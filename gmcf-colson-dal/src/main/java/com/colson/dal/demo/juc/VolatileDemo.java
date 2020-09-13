package com.colson.dal.demo.juc;

/**
 * 一、volatile关键字
 * 当多个线程进行操作共享数据时,可以保证内存中的数据可见.
 * 相较于synchronized是一种较为轻量级的同步策略.
 * 注意:
 * 1.volatile不具备"互斥性"
 * 2.volatile不能保证变量的"原子性"
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyThread td = new MyThread();
        new Thread(td).start();

        while (true) {
//            synchronized (td) { //加锁效率极低,发现锁被占用会阻塞,等待CPU下一次分配内存再判断锁
                if (td.isFlag()) {
                    System.out.println("--------------");
                    break;
                }
//            }
        }
    }


    static class MyThread implements Runnable {

        //volatile关键字修饰实现同步
        private volatile boolean flag = false;

        @Override
        public void run() {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            flag = true;
            System.out.println("flag:" + isFlag());
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
