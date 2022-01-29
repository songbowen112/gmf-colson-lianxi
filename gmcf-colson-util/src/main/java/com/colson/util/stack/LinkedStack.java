package com.colson.util.stack;


import com.colson.common.pkg.BaseDTO;

/**
 * 链式栈(先进后出)
 * @author songbowen
 * @param <E>
 */
public class LinkedStack<E> extends BaseDTO {

    /**
     * 栈顶结点
     */
    transient Node<E> head;

    transient private int size = 0;

    public LinkedStack() {
    }

    /**
     * 入栈操作
     * @param data
     */
    public void push(E data) {
        Node<E> node = new Node<>(head,data);
        head = node;
        size++;
    }

    /**
     * 出栈操作
     * @return
     */
    public E pop() {
        if (head == null) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(size));
        }
        E data = head.data;
        if (size == 1) {
            head = null;
        } else {
            head = head.next;
        }
        size--;
        return data;
    }

    /**
     * 获取栈的大小
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        Node<E> f = head;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<size;i++) {
            sb.append(f.data+",");
            f = f.next;
        }
        return sb.toString().isEmpty()?"[]":"["+sb.toString().substring(0,sb.length()-1)+"]";
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

        private Node(Node<E> next, E data) {

            this.next = next;
            this.data = data;
        }
    }

}
