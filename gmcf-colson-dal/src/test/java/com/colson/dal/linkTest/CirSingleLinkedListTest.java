package com.colson.dal.linkTest;

import com.colson.dal.util.CirSingleLinkedList;

public class CirSingleLinkedListTest {

    public static void main(String[] args) {
        int index = 0;
        int data = 3;
        CirSingleLinkedList cirSingleLinkedList = new CirSingleLinkedList();
        cirSingleLinkedList.add(0);
        cirSingleLinkedList.add(1);
        cirSingleLinkedList.add(2);
        cirSingleLinkedList.add(3);
        cirSingleLinkedList.add(4);
        cirSingleLinkedList.add(5);
        cirSingleLinkedList.add(6);
        System.out.println("单链表的结构为："+cirSingleLinkedList);
        System.out.println("单链表的长度为："+cirSingleLinkedList.size());
        System.out.println("单链表中第"+(index+1)+"个元素为："+cirSingleLinkedList.get(index));
        System.out.println("单链表中数据为"+data+"的下标为："+cirSingleLinkedList.indexOf(data));
        cirSingleLinkedList.addFrom(0,11);
        System.out.println("单链表的结构为："+cirSingleLinkedList);
        cirSingleLinkedList.addFrom(8,110);
        System.out.println("单链表的结构为："+cirSingleLinkedList);
        cirSingleLinkedList.addFrom(6,1);
        System.out.println("单链表的结构为："+cirSingleLinkedList);
        cirSingleLinkedList.addFrom(4,88);
        System.out.println("单链表的结构为："+cirSingleLinkedList);

        System.out.println("----------------分界线------------------");
        cirSingleLinkedList.remove(0);
        System.out.println("单链表的结构为："+cirSingleLinkedList);
//        cirSingleLinkedList.remove(cirSingleLinkedList.size()); //下标越界
//        System.out.println("单链表的结构为："+singleLinkedList);
        cirSingleLinkedList.remove(cirSingleLinkedList.size()-1);
        System.out.println("单链表的结构为："+cirSingleLinkedList);
        cirSingleLinkedList.remove(4);
        System.out.println("单链表的结构为："+cirSingleLinkedList);

    }
}
