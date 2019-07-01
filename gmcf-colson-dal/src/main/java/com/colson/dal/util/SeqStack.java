package com.colson.dal.util;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 顺序栈(先进后出)
 * @author songbowen
 * @param <E>
 */
public class SeqStack<E> implements Serializable {

    transient E[] datas;

    public SeqStack() {
    }

    /**
     * 入栈操作
     * @param data
     */
    public void push(E data) {
        if (null == datas) {
            this.datas = (E[]) new Object[1];
            datas[0] = data;
        } else {
            this.datas = Arrays.copyOf(datas,datas.length+1);
            datas[datas.length-1] = data;
        }
    }

    /**
     * 出栈操作
     * @return
     */
    public E pop() {
        if (null == datas || datas.length == 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(0));
        }
        E result = datas[datas.length-1];
        datas = Arrays.copyOf(datas,datas.length-1);
        return result;
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
        if (null != datas) {
            for (int i=datas.length-1;i>0;i--) {
                sb.append(datas[i]+",");
            }
        }
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
