package com.colson.dal.util;


import java.io.Serializable;

/**
 * 单向链表
 * @author songbowen
 * @param <E>
 */
public class SingleLinkedList<E> implements Serializable {
    /**
     * 头结点
     */
    transient Node<E> first;

    public SingleLinkedList() {
    }

    /**
     * 获取链表长度
     * @return
     */
    public int size() {
        if (null == first) {
            return 0;
        }
        int i = 1;
        Node<E> next = first.next;
        while (null != next) {
            i++;
            next = next.next;
        }
        return i;
    }

    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 示例 :
     * 给定这个链表：1->2->3->4->5
     * 当 k = 2 时，应当返回: 2->1->4->3->5
     * 当 k = 3 时，应当返回: 3->2->1->4->5
     * @param k
     * @return
     */
    public void reverseKGroup(int k) {
        if(first.next == null) {
            return;
        }
        int time = size()/k;
        for (int i=0;i<time;i++) {
            Node n = first;
            first = node(i+k);
        }

    }

    /**
     * 翻转链表(p:pre ; f:first ; n:next)
     * 1. 1 > 2 > 3 > 4 > 5 > null
     * 2. null 1 > 2 > 3 > 4 > 5 > null
     *     p   f   n
     * 3. null < 1   2 > 3 > 4 > 5 > null
     *           p   f   n
     * 4. null < 1 < 2   3 > 4 > 5 > null
     *               p   f   n
     * 5. null < 1 < 2 < 3   4 > 5 > null
     *                   p   f   n
     * 6. null < 1 < 2 < 3 < 4   5 > null
     *                       p   f    n
     * 7. null < 1 < 2 < 3 < 4 < 5   null //不满足遍历条件
     */
    public void reverse() {
        if(first == null) {
            return;
        }
        Node pre = null;
        Node next = first.next;
        while (first.next != null) {
            first.next = pre;
            pre = first;
            first = next;
            next = first.next;
        }
        first.next = pre;
    }

    /**
     * 获取最后一个结点
     * @return
     */
    private Node<E> getLastNode() {
        if (null == first) {
            return null;
        }
        Node node = first;
        while (null != node.next) {
            node = node.next;
        }
        return node;
    }

    /**
     * 向表尾插入元素
     * @param data
     */
    public void add(E data) {
        Node<E> node = new Node(data,null);
        if (null == first) {
            first = node;
        } else {
            getLastNode().next = node;
        }
    }

    /**
     * 向指定位置插入元素
     * @param index
     * @param data
     */
    public void addFrom(int index,E data) {
        if (index<0 || index>this.size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        Node<E> node = new Node(data,null);
        if (null == first) {
            first = node;
        } else {
            if (index == 0) {
                node.next = first;
                first = node;
            } else if (index == this.size()) {
                node(index-1).next = node;
            } else {
                node.next = node(index);
                node(index-1).next = node;
            }
        }
    }

    /**
     * 在指定结点后插入节点
     * @param targetNode
     * @param data
     */
    private void addAfter(Node<E> targetNode,E data) {
        Node<E> node = new Node<E>(data,null);
        if (null !=targetNode && null != targetNode.next) {
            node.next = targetNode.next;
        }
        targetNode.next = node;
    }

    /**
     * 移除指定下标结点
     * @param index
     */
    public void remove(int index) {
        if (index<0 || index>=this.size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        if (index == 0) {
            first = first.next;
        } else if (index == this.size()-1) {
            node(index-1).next = null;
        } else {
            node(index-1).next = node(index+1);
        }
    }

    /**
     * 删除目标结点的下一个结点
     * @param targetNode
     */
    private void deleteNext(Node<E> targetNode) {
        if (null != targetNode.next) {
            if (null != targetNode.next.next) {
                targetNode.next = targetNode.next.next;
            } else {
                targetNode.next = null;
            }
        }
    }

    /**
     * 获取指定下标的数据
     * @param index
     * @return
     */
    public E get(int index) {
        return node(index).data;
    }

    /**
     * 获取指点下标结点
     * @param index
     * @return
     */
    Node<E> node(int index) {
        if (index<0 || index>=this.size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        Node<E> node = first;
        for (int i=0;i<index;i++) {
            node = node.next;
        }
        return node;

    }

    /**
     * 返回指定数据的下标
     * @param data
     * @return
     */
    public int indexOf(E data) {
        int index = 0;
        Node<E> node = first;
        while (null != node) {
            if (data.equals(node.data)) {
                return index;
            }
            index++;
            node = node.next;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<this.size();i++) {
            sb.append(this.get(i)+",");
        }
        return first ==null?"[]":"[" + sb.substring(0,sb.length()-1) + ']';
    }

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size();
    }

    /**
     * 单链表结点内部类
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node() {
            this.next = null;
        }

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

}
