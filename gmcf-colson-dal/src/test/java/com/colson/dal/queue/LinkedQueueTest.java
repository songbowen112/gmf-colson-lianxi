package com.colson.dal.queue;

import com.colson.dal.util.LinkedQueue;

/**
 * Created by Administrator on 2019/7/6.
 */
public class LinkedQueueTest {

    public static void main(String[] args) {
        LinkedQueue linkedQueue = new LinkedQueue();
        System.out.println(linkedQueue);
        linkedQueue.add(1);
        System.out.println(linkedQueue);
        linkedQueue.add(2);
        linkedQueue.add(3);
        linkedQueue.add(4);
        linkedQueue.add(5);
        System.out.println(linkedQueue.indexOf(6));
        System.out.println("队列的长度为："+linkedQueue.size());
        System.out.println(linkedQueue);
        linkedQueue.remove();
        System.out.println(linkedQueue);
        linkedQueue.remove();
        System.out.println(linkedQueue);
        linkedQueue.remove();
        System.out.println(linkedQueue);
        linkedQueue.remove();
        System.out.println(linkedQueue);
        linkedQueue.remove();
        System.out.println(linkedQueue);
        System.out.println("队列的长度为："+linkedQueue.size());

    }
}
