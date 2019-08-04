package com.colson.dal.queue;

import com.colson.dal.util.CirSeqQueue;

public class CirSeqQueueTest {

    public static void main(String[] args) {
        CirSeqQueue cirSeqQueue = new CirSeqQueue();
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(1);
        System.out.println(cirSeqQueue.find(0));
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(2);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(3);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(4);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(5);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(6);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(7);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(8);
        System.out.println(cirSeqQueue);
        cirSeqQueue.add(9);
        System.out.println(cirSeqQueue);
        System.out.println(cirSeqQueue.find(6));
        System.out.println(cirSeqQueue.find(7));
        System.out.println(cirSeqQueue.find(8));
        System.out.println(cirSeqQueue.find(9));
        System.out.println(cirSeqQueue.find(10));
        System.out.println(cirSeqQueue.find(11));
        System.out.println(cirSeqQueue.find(12));
        System.out.println(cirSeqQueue.find(13));
        System.out.println(cirSeqQueue.find(14));
        System.out.println(cirSeqQueue.find(15));
        System.out.println(cirSeqQueue.find(16));
        System.out.println(cirSeqQueue.find(17));
        System.out.println(cirSeqQueue.find(18));
        System.out.println(cirSeqQueue.find(19));



        System.out.println("队列的长度为："+cirSeqQueue.size());

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
