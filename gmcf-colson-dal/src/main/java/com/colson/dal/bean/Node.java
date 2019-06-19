package com.colson.dal.bean;

import org.apache.poi.ss.formula.functions.T;

public class Node {

    private Object data;
    private Node next;

    public Node() {
        this.next = null;
    }

    public Node(Object data, Node next) {
        this.data = data;
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return "{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
