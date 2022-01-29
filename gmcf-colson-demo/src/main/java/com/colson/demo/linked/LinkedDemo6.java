package com.colson.demo.linked;


import com.colson.common.bean.ListNode;
import com.colson.util.LinkedListUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 用单链表保存m个整数，结点结构为[data][link],且|data|<=n(n为正整数)。现要求设计一个算法，对于链表中
 * data的绝对值相等的结点，仅保留第一次出现的结点而删除其余绝对值相等的结点。
 * head>>21>>-15>>-15>>-7>>-15
 * 删除结点后的head为：
 * head>>21>>-15>>-7
 */
public class LinkedDemo6 {
    public static void main(String[] args) {
        ListNode list = new ListNode(0);
        ListNode head = list;

        int[] ins = {1,2,3,-1,2,-2,-2,-3,3,1,4,-4,4,2,-3};
        for (int i=0;i<ins.length;i++) {
            ListNode node = new ListNode(ins[i]);
            head.next = node;
            head = node;
        }
        System.out.println("初始list："+ LinkedListUtils.printLink(list.next));
        deleteSameAbsoluteValue(list);
        System.out.println("结果list："+ LinkedListUtils.printLink(list.next));

    }

    public static void deleteSameAbsoluteValue(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode node = head;
        Set set = new HashSet<>();
        while (node!=null && node.next!=null) {
            int val = (int)node.next.val;
            int absoluteValue = val<0?-val:val;
            if (set.contains(absoluteValue)) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
            set.add(absoluteValue);
        }


    }
}
