package com.colson.dal;

import com.colson.dal.bean.ListNode;

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

        System.out.println(printLink(head));
        head = reverseKGroup(head,3);
        System.out.println(printLink(head));
        System.out.println(size(head));
    }

    /**
     * 步骤分解（D:\repositories\gmf-colson-lianxi\gmcf-colson-dal\src\main\resources\msg\k个一组翻转链表.png）
     *
     * 1.链表分区为 已翻转部分+待翻转部分+未翻转部分
     * 2.每次翻转前，要确定翻转链表的范围，这个必须通过k次循环来确定
     * 3.需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
     * 4.初始需要两个变量 pre和end，pre代表待翻转链表的前驱，end代表待翻转链表的末尾
     * 5.经过k此循环，end到达末尾，记录待翻转链表的后继 next = end.next
     * 6.翻转链表，然后将三部分链表连接起来，然后重置pre和end指针，然后进入下一次循环
     * 7.特殊情况，当翻转部分长度不足k时，在定位end完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
     * 8.时间复杂度为O(n*K) 最好的情况为O(n) 最差的情况未O(n^2)
     * 9.空间复杂度为O(1) 除了几个必须的节点指针外，我们并没有占用其他空间 具体代码如下:
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head,int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;//

            if (end == null) break;
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;

            end = pre;
        }
        return dummy.next;
    }

    public static ListNode reverse(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        ListNode next = head.next;
        while (curr.next!=null) {
            curr.next = pre;
            pre = curr;
            curr = next;
            next = curr.next;
        }
        curr.next = pre;
        return curr;
    }

    public static String printLink(ListNode head) {
        if(head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        ListNode node = head;
        while(node.next != null) {
            sb.append(node.val+",");
            node = node.next;
        }
        sb.append(node.val);
        return "["+sb.toString()+"]";
    }

    public static int size(ListNode head) {
        int size = 0;
        ListNode node = head;
        while(node != null) {
            node = node.next;
            size++;
        }
        return size;
    }
}



