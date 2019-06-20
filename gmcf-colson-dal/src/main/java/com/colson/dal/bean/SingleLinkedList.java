package com.colson.dal.bean;


import java.util.LinkedList;

public class SingleLinkedList<E> {
    /**
     * 头结点
     */
    private Node<E> tNode;

    public SingleLinkedList() {
    }

    /**
     * 获取链表长度
     * @return
     */
    public int size() {
        if (null == tNode) {
            return 0;
        }
        int i = 1;
        Node<E> next = tNode.next;
        while (null != next) {
            i++;
            next = next.next;
        }
        return i;
    }

    /**
     * 获取最后一个结点
     * @return
     */
    private Node<E> getLastNode() {
        if (null == tNode) {
            return null;
        }
        Node node = tNode;
        while (null != node.next) {
            node = node.next;
        }
        return node;
    }

    /**
     * 向表尾插入元素
     * @param data
     */
    public void addLast(E data) {
        Node<E> node = new Node(data,null);
        if (null == tNode) {
            tNode = node;
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
        Node<E> node = new Node(data,null);
        if (null == tNode) {
            tNode = node;
        } else {
            getLastNode().next = node;
        }
    }

    /**
     * 在指定结点后插入节点
     * @param targetNode
     * @param data
     */
    private void addAfter(Node<E> targetNode,E data) {
        Node<E> node = new Node<E>(data,null);
        if (null != targetNode.next) {
            node.next = targetNode.next;
        }
        targetNode.next = node;
    }

    /**
     * 删除目标结点的下一个结点
     * @param targetNode
     */
    public void deleteNext(Node<E> targetNode) {
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

    Node<E> node(int index) {
        if (index >= 0 && index < this.size()) {
            if (null == tNode) {
                return null;
            }
            Node<E> node = tNode;
            for (int i=0;i<index;i++) {
                node = node.next;
            }
            return node;
        }
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 返回指定数据的下标
     * @param data
     * @return
     */
    public int indexOf(E data) {
        int index = 0;
        Node<E> node = tNode;
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
        return tNode==null?"[]":"[" + sb.substring(0,sb.length()-1) + ']';
    }

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
