package com.colson.dal.util;

import java.io.Serializable;

/**
 * 循环单链表
 * @author songbowen
 * @param <E>
 */
public class CirSingleLinkedList<E> implements Serializable {

    transient Node<E> last;

    transient int size = 0;

    public CirSingleLinkedList() {
    }

    /**
     * 获取链表的长度
     */
    public int size() {
        return size;
    }

    /**
     * 向表尾插入元素
     * @param data
     */
    public void add(E data) {
        Node<E> l = last;
        Node<E> node = new Node<>(null,data);
        last = node;
        if (size == 1) {
            node.next = l;
            l.next = node;
        }
        if (size > 1) {
            node.next = l.next;
            l.next = node;
        }
        size++;
    }

    /**
     * 向指定位置插入元素
     * @param index
     * @param data
     */
    public void addFrom(int index,E data) {
        if (index<0 || index>this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        Node<E> node = new Node(null,data);
        if (null == last) {
            last = node;
        } else {
            if (index == 0) {
                node.next = node(index);
                last.next = node;
            } else if (index == this.size) {
                node.next = node(index-1).next;
                node(index-1).next = node;
                last = node;
            } else {
                node.next = node(index);
                node(index-1).next = node;
            }
        }
        size++;
    }

    /**
     * 移除指定下标结点
     * @param index
     */
    public void remove(int index) {
        if (index<0 || index>=this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        if (index == 0) {
            last.next = last.next.next;
        } else if (index == this.size-1) {
            node(index-1).next = node(index).next;
            last = node(index-1);
        } else {
            node(index-1).next = node(index).next;
        }
        size--;
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
        if (index<0 || index>=this.size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        if (size == 1) {
            return last;
        }
        Node<E> node = last.next;
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
        if (last != null) {
            for (int i=0;i<size;i++) {
                if (get(i).equals(data)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<this.size;i++) {
            sb.append(this.get(i)+",");
        }
        return this.size<=0?"[]":"[" + sb.substring(0,sb.length()-1) + ']';
    }

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size;
    }

    private static class Node<E>{

        Node<E> next;

        E data;

        private Node(Node<E> next,E data) {
            this.next = next;
            this.data = data;
        }
    }
}
