package com.colson.dal;

import com.colson.dal.bean.Node;
import com.colson.dal.bean.SingleLinkedList;

public class MyLinkTest {

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList(new Node(99,null));
        singleLinkedList.add(0);
        System.out.println(singleLinkedList);
        singleLinkedList.add(1);
        System.out.println(singleLinkedList);
        singleLinkedList.add(2);
        System.out.println(singleLinkedList);
        singleLinkedList.add(3);
        System.out.println(singleLinkedList);
        singleLinkedList.add(4);
        System.out.println(singleLinkedList.size());

        System.out.println(singleLinkedList.indexOf(2));

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.add(1);
        singleLinkedList2.add(2);
        singleLinkedList2.add(10);
        System.out.println(singleLinkedList2);
        singleLinkedList2.getLastNode();
    }
}
