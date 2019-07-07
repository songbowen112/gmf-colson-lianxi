package com.colson.dal.queue;

import com.colson.dal.util.SeqQueue;

public class SeqQueueTest {
    public static void main(String[] args) {
        SeqQueue seqQueue = new SeqQueue();
        System.out.println(seqQueue);
        seqQueue.add(1);
        System.out.println(seqQueue);
        seqQueue.add(2);
        seqQueue.add(3);
        seqQueue.add(4);
        seqQueue.add(5);
        seqQueue.add(6);
        System.out.println("队列的长度为："+seqQueue.size());
        System.out.println(seqQueue);
        seqQueue.remove();
        System.out.println(seqQueue);
        seqQueue.remove();
        System.out.println(seqQueue);
        seqQueue.remove();
        System.out.println(seqQueue);
        seqQueue.remove();
        System.out.println(seqQueue);
        seqQueue.remove();
        System.out.println(seqQueue);
        seqQueue.remove();
        System.out.println(seqQueue);
        System.out.println("队列的长度为："+seqQueue.size());

    }
}
