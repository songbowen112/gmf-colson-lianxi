package com.colson.dal.util;

import java.io.Serializable;

/**
 * 顺序栈
 * @author songbowen
 * @param <E>
 */
public class SeqStack<E> implements Serializable {

    transient E[] data;

    transient int top = -1;

    public SeqStack() {
    }

    /**
     * 入栈操作
     * @param data
     */
    public void push(E data) {

    }

    /**
     * 出栈操作
     * @return
     */
    public E pop() {

        return null;
    }

    /**
     * 获取栈的大小
     */
    public int size() {
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return "";
    }

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.data.length;
    }

}
