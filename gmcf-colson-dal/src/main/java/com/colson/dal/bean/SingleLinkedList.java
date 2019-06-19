package com.colson.dal.bean;


import org.apache.poi.ss.formula.functions.T;


public class SingleLinkedList {
    /**
     * 头结点
     */
    private Node tNode;

    public SingleLinkedList() {
    }

    public SingleLinkedList(Node tNode) {
        this.tNode = tNode;
    }

    public Node gettNode() {
        return tNode;
    }

    public void settNode(Node tNode) {
        this.tNode = tNode;
    }

    public int size() {
        if (null == tNode) {
            return 0;
        }
        int i = 1;
        Node next = tNode.getNext();
        while (null != next) {
            i++;
            next = next.getNext();
        }
        return i;
    }

    public Node getLastNode() {
        if (null == tNode) {
            return null;
        }
        Node next = tNode;
        while (null != next.getNext()) {
            next = next.getNext();
        }
        return next;
    }

    /**
     * 向单链表插入元素
     * @param data
     */
    public void add(Object data) {
        Node node = new Node(data,null);
        if (null == tNode) {
            tNode = node;
        } else {
            getLastNode().setNext(node);
        }
    }

    /**
     * 在指定结点后插入节点
     * @param targetNode
     * @param data
     */
    public void add(Node targetNode,Object data) {
        Node node = new Node(data,null);
        if (null != targetNode.getNext()) {
            node.setNext(targetNode.getNext());
        }
        targetNode.setNext(node);
    }

    public void deleteNext(Node targetNode) {
        if (null != targetNode.getNext()) {
            if (null != targetNode.getNext().getNext()) {
                targetNode.setNext(targetNode.getNext().getNext());
            } else {
                targetNode.setNext(null);
            }
        }
    }

    public int indexOf(Object data) {
        int index = 1;
        Node node = tNode.getNext();
        while (null != node) {
            if (data.equals(node.getData())) {
                return index;
            }
            index++;
            node = node.getNext();
        }
        return -1;
    }

    @Override
    public String toString() {
        return tNode==null?"[]":"[" + tNode + ']';
    }
}
