package com.solson.dal.linked;


import com.colson.util.linked.SingleLinkedList;

public class SingleLinkedListTest {

    public static void main(String[] args) {
        int index = 5;
        int data = 3;
        SingleLinkedList list = new SingleLinkedList();
        list.add(0);
        list.remove(0);
//        System.out.println(list);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(3);
        list.add(4);
        list.add(3);
        System.out.println("单链表的结构为："+list);
//        list.reverse();
//        System.out.println(list);
//        System.out.println("单链表的长度为："+list.size());
//        System.out.println("单链表下标为"+index+"的数据为："+list.get(index));
//        System.out.println("单链表中数据为"+data+"的下标为："+list.indexOf(data));
//        list.addFrom(0,11);
//        System.out.println("单链表的结构为："+list);
//        list.addFrom(list.size(),110);
//        System.out.println("单链表的结构为："+list);
//        list.addFrom(6,1);
//        System.out.println("单链表的结构为："+list);
//        list.addFrom(4,88);
//        System.out.println("单链表的结构为："+list);
//
//        System.out.println("----------------分界线------------------");
//        list.remove(0);
//        System.out.println("单链表的结构为："+list);
////        list.remove(list.size();//下标越界
////        System.out.println("单链表的结构为："+list);
//        list.remove(list.size()-1);
//        System.out.println("单链表的结构为："+list);
//        list.remove(4);
//        System.out.println("单链表的结构为："+list);
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        ListNode curr = head;
        while(null != curr) {
            System.out.print(curr.val);
            curr = curr.next;
        }
        System.out.println();

        ListNode node = removeNthFromEnd(head, 2);
        System.out.println("----------------分界线------------------");
        while(null != node) {
            System.out.print(node.val);
            node = node.next;
        }

    }

    /**
     * Definition for singly-linked list.
     */
     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int index = 0;
        int len = 0;
        ListNode curr = head;
        while (null != curr) {
            len++;
            curr = curr.next;
        }
        int[] ints = new int[len];
        curr = head;
        while (null != curr) {
            ints[index++] = curr.val;
            curr = curr.next;
        }
        if(n > ints.length) {
            return null;
        }
        ints[ints.length-n] = -1;
        ListNode newHead = new ListNode();
        curr = newHead;
        for(int i=0;i<ints.length;i++) {
            if(-1 == ints[i]) {
                continue;
            }
            ListNode node = new ListNode();
            node.val = ints[i];
            curr.next = node;
            curr = curr.next;
        }
        return newHead.next;
    }
}
