package com.colson.dal.demo.linked;

import com.colson.dal.bean.ListNode;
import com.colson.dal.util.LinkedListUtils;

/**
 * 一个带有表头结点的单链表，头指针为list，在不改变链表的前提下，设计一个尽可能搞笑的算法，查找链表中
 * 倒数第k个位置上的结点（k为正整数）。若查找成功则输出该结点的data值，并返回1；否则只返回0。
 */
public class LinkedDemo5 {
    public static void main(String[] args) {
        ListNode list = new ListNode(0);
        ListNode head = list;

        for (int i=1;i<10;i++) {
            ListNode node = new ListNode(i);
            head.next = node;
            head = node;
        }
        System.out.println("初始list："+LinkedListUtils.printLink(list.next));

        System.out.println("是否有满足的结果："+(findLastNode(list,5)==1?"是":"否"));
    }

    public static int findLastNode(ListNode list, int k) {
        if (k<1 || k>LinkedListUtils.size(list.next)) {
            return 0;
        }
        ListNode head = list.next;
        for (int i=0;i<LinkedListUtils.size(list.next)-k;i++) {
            head = head.next;
        }
        System.out.println("倒数第"+k+"个结点的值为："+head.val);
        return 1;
    }

}
