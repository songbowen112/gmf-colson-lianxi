package com.colson.dal.linkTest;

import com.colson.dal.util.SingleLinkedList;

public class SingleLinkTest {

    public static void main(String[] args) {
        int index = 6;
        int data = 3;
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(0);
        singleLinkedList.add(1);
        singleLinkedList.add(2);
        singleLinkedList.add(3);
        singleLinkedList.add(30);
        singleLinkedList.add(23);
        singleLinkedList.add(4);
        System.out.println("单链表的结构为："+singleLinkedList);
        System.out.println("单链表的长度为："+singleLinkedList.size());
        System.out.println("单链表下标为"+index+"的数据为："+singleLinkedList.get(index));
        System.out.println("单链表中数据为"+data+"的下标为："+singleLinkedList.indexOf(data));
        singleLinkedList.addFrom(0,11);
        System.out.println("单链表的结构为："+singleLinkedList);
        singleLinkedList.addFrom(8,110);
        System.out.println("单链表的结构为："+singleLinkedList);
        singleLinkedList.addFrom(6,1);
        System.out.println("单链表的结构为："+singleLinkedList);
        singleLinkedList.addFrom(4,88);
        System.out.println("单链表的结构为："+singleLinkedList);

        System.out.println("----------------分界线------------------");
        singleLinkedList.remove(0);
        System.out.println("单链表的结构为："+singleLinkedList);
//        singleLinkedList.remove(singleLinkedList.size();//下标越界
//        System.out.println("单链表的结构为："+singleLinkedList);
        singleLinkedList.remove(singleLinkedList.size()-1);
        System.out.println("单链表的结构为："+singleLinkedList);
        singleLinkedList.remove(4);
        System.out.println("单链表的结构为："+singleLinkedList);

    }
}
