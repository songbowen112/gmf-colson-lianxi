package com.colson.dal.queue;

import com.colson.dal.util.SeqQueue2;

public class SeqQueue2Test {

    public static void main(String[] args) {
        SeqQueue2 seqQueue2 = new SeqQueue2();
        System.out.println(seqQueue2);
        seqQueue2.add(1);
        System.out.println(seqQueue2.find(0));
        System.out.println(seqQueue2);
        seqQueue2.add(2);
        System.out.println(seqQueue2);
        seqQueue2.add(3);
        System.out.println(seqQueue2);
        seqQueue2.add(4);
        System.out.println(seqQueue2);
        seqQueue2.add(5);
        System.out.println(seqQueue2);
        seqQueue2.add(6);
        System.out.println(seqQueue2);
        System.out.println("队列的长度为："+seqQueue2.size());

        seqQueue2.del();
        System.out.println(seqQueue2);
        seqQueue2.del();
        System.out.println(seqQueue2);
        seqQueue2.del();
        System.out.println(seqQueue2);
        seqQueue2.del();
        System.out.println(seqQueue2);
        seqQueue2.del();
        System.out.println(seqQueue2);
        seqQueue2.del();
        System.out.println(seqQueue2);
//        seqQueue2.del();
        System.out.println("队列的长度为："+seqQueue2.size());

    }
}
