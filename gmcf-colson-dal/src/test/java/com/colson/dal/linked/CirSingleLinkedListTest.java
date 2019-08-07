package com.colson.dal.linked;

import com.colson.dal.util.linked.CirSingleLinkedList;

public class CirSingleLinkedListTest {

    public static void main(String[] args) {
        int index = 0;
        int data = 3;
        CirSingleLinkedList list = new CirSingleLinkedList();
        list.add(0);
        list.remove(0);
        System.out.println(list);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println("单链表的结构为："+list);
        System.out.println("单链表的长度为："+list.size());
        System.out.println("单链表中第"+(index+1)+"个元素为："+list.get(index));
        System.out.println("单链表中数据为"+data+"的下标为："+list.indexOf(data));
        list.addFrom(0,11);
        System.out.println("单链表的结构为："+list);
        list.addFrom(list.size(),110);
        System.out.println("单链表的结构为："+list);
        list.addFrom(6,1);
        System.out.println("单链表的结构为："+list);
        list.addFrom(4,88);
        System.out.println("单链表的结构为："+list);

        System.out.println("----------------分界线------------------");
        list.remove(0);
        System.out.println("单链表的结构为："+list);
//        list.remove(list.size()); //下标越界
//        System.out.println("单链表的结构为："+singleLinkedList);
        list.remove(list.size()-1);
        System.out.println("单链表的结构为："+list);
        list.remove(4);
        System.out.println("单链表的结构为："+list);

    }
}
