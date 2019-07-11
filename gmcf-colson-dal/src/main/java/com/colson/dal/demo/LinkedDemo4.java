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
        ListNode head = new ListNode('n');
        ListNode node = head;
        char letter = 'a';
        for (int i=1;i<10;i++) {

            ListNode l = new ListNode(letter++);
            node.next = l;
            node = node.next;
        }

        ListNode head2 = new ListNode('m');
        ListNode node2 = head2;
        letter = 'c';
        for (int i=1;i<10;i++) {
            ListNode l = new ListNode(letter++);
            node2.next = l;
            node2 = node2.next;
        }

        System.out.println(LinkedListUtils.printLink(head));
        System.out.println(LinkedListUtils.printLink(head2));

        System.out.println(findSameLocation(head,head2));
    }

    public static ListNode findSameLocation(ListNode str1, ListNode str2) {
        ListNode head1 = str1.next;
        ListNode head2 = str2.next;

        if (head1==null || head2==null) {
            return null;
        }
        int size1 = LinkedListUtils.size(head1);
        int size2 = LinkedListUtils.size(head2);

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
            for (int i=0;i<size1-1;i++) {
                f1 = f1.next;
            }
            for (int j=0;j<size2-1;j++) {
                f2 = f2.next;
            }
            findLetter(f1,f2,head1,head2,size1,size2);
        }
        return last1;
    }
}
