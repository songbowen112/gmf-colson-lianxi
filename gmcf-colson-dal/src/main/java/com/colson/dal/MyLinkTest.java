package com.colson.dal;

import com.colson.dal.bean.SingleLinkedList;

import java.util.LinkedList;
import java.util.List;

public class MyLinkTest {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addLast(0);
        singleLinkedList.addLast(1);
        singleLinkedList.addLast(2);
        singleLinkedList.addLast(3);
        singleLinkedList.addLast(30);
        singleLinkedList.addLast(23);
        singleLinkedList.addLast(4);
        System.out.println(singleLinkedList);
        System.out.println(singleLinkedList.size());
        System.out.println(singleLinkedList.get(6));

        System.out.println(singleLinkedList.indexOf(23));

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addLast(1);
        singleLinkedList2.addLast(2);
        singleLinkedList2.addLast(10);
        System.out.println(singleLinkedList2);
        List list = new LinkedList();
    }
}
