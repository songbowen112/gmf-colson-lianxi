package com.colson.dal;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 */
public class LinkedDemo {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode node = head;

        for (int i=1;i<10;i++) {
            ListNode l = new ListNode(i);
            node.next = l;
            node = node.next;
        }
        System.out.println(size(head));

        System.out.println(head);
        head = reverseKGroup(head,2);
        System.out.println(head);
        System.out.println(size(head));
    }

    public static ListNode reverseKGroup(ListNode head,int k) {
        if(head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = head.next;
        while (head.next!=null) {
            head.next = pre;
            pre = head;
            head = next;
            next = head.next;
        }
        head.next = pre;
        return head;
    }

    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static int size(ListNode head) {
        int size = 0;
        if(head == null) {
            return size;
        }
        size = 1;
        ListNode node = head;
        while(node.next != null) {
            node = node.next;
            size++;
        }
        return size;
    }
}



