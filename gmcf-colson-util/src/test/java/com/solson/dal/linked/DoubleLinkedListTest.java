package com.solson.dal.linked;


import com.colson.util.linked.DoubleLinkedList;

/**
 * Created by Administrator on 2019/6/22.
 */
public class DoubleLinkedListTest {

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        System.out.println(list);
        list.add(12);
        list.remove(0);
        System.out.println(list);
        list.add(5);
        list.add(4);
        list.add(5);
        list.add(2);
        list.add(6);
        list.add(3);
        System.out.println(list);

        list.addFrom(0,11);
        list.addFrom(3,99);
        list.addFrom(list.size(),1);
        list.addFrom(list.size(),2);
        list.addFrom(list.size(),3);
        list.addFrom(list.size(),4);
        list.addFrom(list.size(),5);

        System.out.println(list);


        System.out.println(list.indexOf(11));
        System.out.println(list);

    }
}
