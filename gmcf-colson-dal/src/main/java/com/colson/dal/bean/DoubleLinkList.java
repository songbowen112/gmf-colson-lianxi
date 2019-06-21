package com.colson.dal.bean;


import java.util.LinkedList;

/**
 * 双向链表
 * @author songbowen
 * @param <E>
 */
public class DoubleLinkList<E> {

    Node<E> first;

    Node<E> last;

    int size;

    public DoubleLinkList() {
    }

    /**
     * 向表尾插入元素
     * @param data
     */
    public void add(E data) {
        Node<E> node = new Node<>(null,data,null);
        if (null == first) {

        }
    }

    /**
     * 向指定位置插入元素
     * @param index
     * @param data
     */
    public void addFrom(int index,E data) {

    }

    /**
     * 移除指定下标结点
     * @param index
     */
    public void remove(int index) {

    }

    /**
     * 获取指定下标的数据
     * @param index
     * @return
     */
    public E get(int index) {

        return null;
    }

    /**
     * 获取指点下标结点
     * @param index
     * @return
     */
    Node<E> node(int index) {

        return null;
    }

    /**
     * 返回指定数据的下标
     * @param data
     * @return
     */
    public int indexOf(E data) {

        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<this.size;i++) {
            sb.append(this.get(i)+",");
        }
        return "[" + sb.substring(0,sb.length()-1) + ']';
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
