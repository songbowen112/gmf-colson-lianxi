package com.colson.dal.demo;

import com.colson.dal.util.SingleLinkedList;

/**
 * 1.设计一个递归算法，删除不带头结点的单链表l中所有值为x的结点
 */
public class LinkedDemo2 {


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
