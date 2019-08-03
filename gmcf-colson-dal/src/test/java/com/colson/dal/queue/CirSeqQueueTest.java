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
//        seqQueue2.del();
        System.out.println("队列的长度为："+cirSeqQueue.size());

    }
}
