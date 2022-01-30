package com.solson.dal.queue;


import com.colson.util.queue.CirSeqQueue;

public class CirSeqQueueTest {

    public static void main(String[] args) {
        CirSeqQueue cirSeqQueue = new CirSeqQueue();
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(1);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(2);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(3);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(4);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(5);
        System.out.println(cirSeqQueue);
        System.out.println("队列的长度为："+cirSeqQueue.size());

        System.out.println("-------插入完毕，开始查找--------");
        System.out.println(cirSeqQueue.find(6));
        System.out.println(cirSeqQueue.find(7));
        System.out.println(cirSeqQueue.find(8));
        System.out.println(cirSeqQueue.find(9));
        System.out.println(cirSeqQueue.find(10));

        System.out.println("-------开始删除--------");
        System.out.println(cirSeqQueue);
        cirSeqQueue.del();
        System.out.println(cirSeqQueue);
        cirSeqQueue.del();
        System.out.println(cirSeqQueue);
        cirSeqQueue.del();
        System.out.println(cirSeqQueue);
        cirSeqQueue.del();
        System.out.println(cirSeqQueue);
        cirSeqQueue.del();
        System.out.println(cirSeqQueue);

        System.out.println("队列的长度为："+cirSeqQueue.size());
    }
}
