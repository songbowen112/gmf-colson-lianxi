package com.colson.dal.demo;

import com.colson.dal.bean.ListNode;
import com.colson.dal.util.LinkedListUtils;

/**
 * 假定采用带头结点的单链表保存单词，当两个单词有相同的后缀时，可共享相同的后缀存储空间，例如，
 * ‘loading’ 和 ‘being’共用i、n、g三个结点，设str1和str2分别指向两个单词所在单链表的头结点，找出
 * 由str1和str2所指向两个链表共同后缀的起始位置（如字符i的位置）。
 */
public class LinkedDemo4 {

    public static void main(String[] args) {
        ListNode head = new ListNode(null);//第一个单链表的头结点
        ListNode node = head;
        char[] c1 = {'p','s','y','c','h','o','l','o','g','y'};
        for (int i=0;i<c1.length;i++) {
            ListNode l = new ListNode(c1[i]);
            node.next = l;
            node = node.next;
        }

        ListNode head2 = new ListNode(null);//第二个单链表的头结点
        ListNode node2 = head2;
        char[] c2 = {'b','i','o','l','o','g','y'};
        for (int i=0;i<c2.length;i++) {
            ListNode l = new ListNode(c2[i]);
            node2.next = l;
            node2 = node2.next;
        }

        System.out.println(LinkedListUtils.printLink(head));
        System.out.println(LinkedListUtils.printLink(head2));

        System.out.println(findSameLocation(head,head2));
    }

    public static int findSameLocation(ListNode str1, ListNode str2) {
        if (str1.next==null || str2.next==null) {
            return 0;
        }

        return findLetter(str1,str2);
    }

    public static int findLetter(ListNode str1,ListNode str2) {
        int size1 = LinkedListUtils.size(str1.next);//第一个单链表的长度
        int size2 = LinkedListUtils.size(str2.next);//第二个单链表的长度

        int length = size1>size2?size2:size1;

        for (int i=1;i<=length;i++) {
            char a1 = (char)LinkedListUtils.getLastData(str1,i);
            char a2 = (char)LinkedListUtils.getLastData(str2,i);
            if (i==length && a1==a2) {
                return i;
            }
            if (a1!=a2) {
                return --i;
            }
        }

        return 0;
    }
}
