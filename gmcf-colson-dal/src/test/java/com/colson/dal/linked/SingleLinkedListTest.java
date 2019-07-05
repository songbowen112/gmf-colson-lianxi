package com.colson.dal.linked;

import com.colson.dal.util.SingleLinkedList;

public class SingleLinkedListTest {

    public static void main(String[] args) {
        int index = 5;
        int data = 3;
        SingleLinkedList list = new SingleLinkedList();
        list.add(0);
        list.remove(0);
        System.out.println(list);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(6);
        list.add(8);
        list.add(7);
        list.add(4);
        System.out.println("单链表的结构为："+list);
        list.reverse();
        System.out.println(list);
        System.out.println("单链表的长度为："+list.size());
        System.out.println("单链表下标为"+index+"的数据为："+list.get(index));
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
//        list.remove(list.size();//下标越界
//        System.out.println("单链表的结构为："+list);
        list.remove(list.size()-1);
        System.out.println("单链表的结构为："+list);
        list.remove(4);
        System.out.println("单链表的结构为："+list);

    }
}
