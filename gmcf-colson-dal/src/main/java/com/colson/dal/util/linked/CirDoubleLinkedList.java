package com.colson.dal.util.linked;


import java.io.Serializable;

/**
 * 循环双链表
 * @author songbowen
 * @param <E>
 */
public class CirDoubleLinkedList<E> implements Serializable {

    transient Node<E> first;

    transient Node<E> last;

    transient int size = 0;

    public CirDoubleLinkedList() {
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
        Node<E> f = first;
        Node<E> node = new Node<>(l,data,null);
        last = node;

        if (size == 0) {
            first = node;
        } else {
            node.next = f;
            l.next = node;
            f.prev = node;
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
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> f = first;
            Node<E> l = last;
            if (index == 0) {
                first = f.next;
                first.prev = l;
                l.next = first;
            } else if (index == this.size-1) {
                last = l.prev;
                last.next = f;
                f.prev = last;
            } else {
                node(index+1).prev = node(index-1);
                node(index-1).next = node(index+1);
            }
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
        if (first != null) {
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

        Node<E> prev;

        Node<E> next;

        E data;

        private Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }
}
