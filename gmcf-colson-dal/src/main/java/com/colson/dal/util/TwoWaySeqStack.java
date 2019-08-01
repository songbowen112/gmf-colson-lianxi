package com.colson.dal.util;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 双向顺序栈
 */
public class TwoWaySeqStack<E> implements Serializable {

    /**
     * 共享存储区
     */
    private Object[] datas = new Object[100];

    /**
     * 左栈顶下标
     */
    private int leftIndex;

    /**
     * 右栈顶下标
     */
    private int rightIndex;

    public TwoWaySeqStack() {
    }

    /**
     * 入左栈
     * @param data
     */
    public void pushLeft(E data) {
        if (leftIndex != rightIndex) {

        }
    }

    /**
     * 入右栈
     * @param data
     */
    public void pushRight(E data) {

    }

    /**
     * 出左栈
     * @return
     */
    public E popLeft() {
        return null;
    }

    /**
     * 出右栈
     * @return
     */
    public E popRight() {
        return null;
    }

    /**
     * 获取栈的大小
     */
    public int size() {
        return datas.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb.toString().isEmpty()?"[]":"["+sb.toString().substring(0,sb.length()-1)+"]";
    }

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+this.datas.length;
    }
}
