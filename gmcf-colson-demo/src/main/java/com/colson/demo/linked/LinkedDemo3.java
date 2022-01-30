package com.colson.demo.linked;


import com.colson.util.linked.SingleLinkedList;

/**
 * 设l为带头结点的单链表，编写算法实现从尾到头反向输出每个结点的值
 */
public class LinkedDemo3 {
    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();

        for (int i=1;i<10;i++) {
            list.add(i);
        }
        list.add(10);
        list.add(11);
        list.add(12);
        System.out.println("初始list："+list);

        System.out.println("第一种方式："+printDesc(list));
        System.out.println("第二种方式："+printDesc2(list.first,new StringBuilder()));
    }


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
