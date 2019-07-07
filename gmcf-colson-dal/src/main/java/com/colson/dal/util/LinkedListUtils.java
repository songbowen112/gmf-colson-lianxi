package com.colson.dal.util;


import java.util.LinkedList;

/**
 * Created by Administrator on 2019/7/7.
 */
public class LinkedListUtils {
    /**
     * 1.设计一个递归算法，删除不带头结点的单链表l中所有值为x的结点
     */
    public static void delNodeIs(SingleLinkedList.Node node,Integer x) {
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

    /**
     * 2.设l为带头结点的单链表，编写算法实现从尾到头反向输出每个结点的值
     */
    public static String printDesc(SingleLinkedList l) {
        StringBuilder sb = new StringBuilder();
        for (int i=l.size()-1;i>=0;i--) {
            sb.append(l.get(i)+",");
        }
        return sb.toString().isEmpty()?"[]":"["+sb.toString().substring(0,sb.length()-1)+"]";
    }

    public static String printDesc2(SingleLinkedList.Node node,StringBuilder sb) {
        if (node.next != null) {
            printDesc2(node.next,sb);
        }
        sb.append(node.data+",");
        return sb.toString().isEmpty()?"[]":"["+sb.toString().substring(0,sb.length()-1)+"]";
    }
}
