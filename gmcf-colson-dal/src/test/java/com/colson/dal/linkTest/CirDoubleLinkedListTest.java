package com.colson.dal.linkTest;

import com.colson.dal.util.CirDoubleLinkedList;

/**
 * Created by Administrator on 2019/6/22.
 */
public class CirDoubleLinkedListTest {

    public static void main(String[] args) {
        CirDoubleLinkedList list = new CirDoubleLinkedList();
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

        System.out.println(list.get(4));
        System.out.println(list.get(0));
        list.remove(4);
        list.remove(list.size()-1);
        System.out.println(list);
        System.out.println(list.indexOf(5));
        System.out.println(list);

    }
}
