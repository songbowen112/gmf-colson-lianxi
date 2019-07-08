package com.colson.dal.util;


import com.colson.dal.LinkedDemo;
import com.colson.dal.bean.ListNode;

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

    /**
     * 3.一个带有表头结点的单链表，头指针为list，在不改变链表的前提下，设计一个尽可能搞笑的算法，查找链表中
     * 倒数第k个位置上的结点（k为正整数）。若查找成功则输出该结点的data值，并返回1；否则只返回0。
     */
    public static int findLastNode(ListNode list,int k) {


        return 0;
    }

    /**
     * 4.假定采用带头结点的单链表保存单词，当两个单词有相同的后缀时，可共享相同的后缀存储空间，例如，
     * ‘loading’ 和 ‘being’共用i、n、g三个结点，设str1和str2分别指向两个单词所在单链表的头结点，找出
     * 由str1和str2所指向两个链表共同后缀的起始位置（如字符i的位置）。
     */
    public static ListNode findSameLocation(ListNode str1,ListNode str2) {
        ListNode head1 = str1.next;
        ListNode head2 = str2.next;

        if (head1==null || head2==null) {
            return null;
        }
        int size1 = LinkedDemo.size(head1);
        int size2 = LinkedDemo.size(head2);

        while (head1.next!=null) {
            head1 = head1.next;
        }
        while (head2.next!=null) {
            head2 = head2.next;
        }

        return findLetter(head1,head2,str1.next,str2.next,size1,size2);
    }

    public static ListNode findLetter(ListNode last1,ListNode last2,ListNode head1,ListNode head2,int size1,int size2) {
        ListNode f1 = head1;
        ListNode f2 = head2;

        if (last1.val.equals(last2.val)) {
            for (int i=0;i<size1-1-i;i++) {
                f1 = f1.next;
            }
            for (int j=0;j<size2-1-j;j++) {
                f2 = f2.next;
            }
            findLetter(f1,f2,head1,head2,size1,size2);
        }
        return last1.next;
    }
}
