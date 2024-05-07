package com.colson.demo.linked;


import com.colson.util.linked.SingleLinkedList;

/**
 * 设计一个递归算法，删除不带头结点的单链表l中所有值为x的结点
 */
public class LinkedDemo2 {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();

        for (int i=1;i<10;i++) {
            list.add(i);
        }
        list.add(2);
        list.add(3);
        list.add(2);
        System.out.println(list);

        delNodeIs(list.head,2);
        System.out.println(list);
    }

    public static void delNodeIs(SingleLinkedList.Node node, Integer x) {
        if (node==null || node.next==null) {
            return;
        }
//        if (x.equals(node.data)) {
//            node = node.next;
//        }
        if (x.equals(node.next.data)) {
            node.next = node.next.next;
            delNodeIs(node,x);
        } else {
            delNodeIs(node.next,x);
        }
    }
}
