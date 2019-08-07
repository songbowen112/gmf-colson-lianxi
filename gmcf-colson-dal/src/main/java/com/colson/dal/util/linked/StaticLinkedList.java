package com.colson.dal.util.linked;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 静态链表
 * @author songbowen
 * @param <E>
 */
public class StaticLinkedList<E> {

    transient Node[] datas = new Node[100];

    transient int head = -1;

    transient int size;

    transient Set<Integer> set = new HashSet();

    public StaticLinkedList() {
    }

    /**
     * 获取长度
     */
    public int size() {
        return size;
    }

    /**
     * 向表尾插入元素
     * @param data
     */
    public void add(E data) {
        Node node = new Node(-1,data);
        int newIndex = getRandomIndex();

        if (head == -1) {
            head = newIndex;
        } else {
            Node first = datas[head];
            while (first.next!=-1) {
                first = datas[first.next];
            }
            first.next = newIndex;
        }
        datas[newIndex] = node;
        size++;
    }

    /**
     * 移除指定下标结点
     */
    public void remove(int index) {
        if (null==datas || size==0 || index>=size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(0));
        }
        if (size == 1) {
            set.remove(head);
            datas[head] = null;
            head = -1;
        } else {
            Node first = datas[head];
            if (index == 0) {
                set.remove(head);
                datas[head] = null;
                head = first.next;

            } else {
                for (int i=0;i<index-1;i++){//获取目标节点前面的那个节点
                    first = datas[first.next];
                }
                int i = first.next;
                first.next = datas[i].next;
                set.remove(i);
                datas[i] = null;
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
        if (null==datas || size==0 || size<=index) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        Node node = datas[head];
        for (int i=0;i<index;i++) {
            node = datas[node.next];
        }
        return (E) node.data;
    }

    /**
     * 返回指定数据的下标
     * @param data
     * @return
     */
    public int indexOf(E data) {
        if (null==datas || datas.length==0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(0));
        }
        for (int i=0;i<size;i++) {
            if (get(i).equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public int getRandomIndex() {
        int i = (int)(Math.random()*(datas.length));
        while (set.contains(i)) {
            i = (int)(Math.random()*(datas.length));
        }
        set.add(i);
        return i;
    }

    @Override
    public String toString() {
        if (null == datas) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<size;i++) {
            sb.append(this.get(i)+",");
        }
        return sb.toString().isEmpty()?"[]":"[" + sb.substring(0,sb.length()-1) + ']';
    }

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.size();
    }

    private static class Node<E>{
        int next;
        E data;

        public Node(int next, E data) {
            this.next = next;
            this.data = data;
        }
    }
}
