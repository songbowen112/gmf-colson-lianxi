package com.colson.dal.util;


import com.colson.dal.bean.ListNode;

/**
 * Created by Administrator on 2019/7/7.
 */
public class LinkedListUtils {

    /**
     * 计算链表长度
     * @param head
     * @return
     */
    public static int size(ListNode head) {
        int size = 0;
        ListNode node = head;
        while(node != null) {
            node = node.next;
            size++;
        }
        return size;
    }

    /**
     * 翻转单链表
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if(head==null || head.next==null) {
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

    public static ListNode reverse2(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode pre = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        head = pre;
        return head;
    }

    /**
     * 输出单链表
     * @param head
     * @return
     */
    public static String printLink(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode node = head;
        while(node != null) {
            sb.append(node.val+",");
            node = node.next;
        }
        return sb.toString().isEmpty()?"[]":"["+sb.toString().substring(0,sb.length()-1)+"]";
    }

    /**
     * 获取带头结点的单链表倒数第k个结点的值
     * @param list
     * @param k
     * @return
     */
    public static Object getLastData(ListNode list, int k) {
        ListNode head = list.next;
        if (k<1 || k>LinkedListUtils.size(head)) {
            return null;
        }
        for (int i=0;i<LinkedListUtils.size(list.next)-k;i++) {
            head = head.next;
        }
        return head.val;
    }
}
