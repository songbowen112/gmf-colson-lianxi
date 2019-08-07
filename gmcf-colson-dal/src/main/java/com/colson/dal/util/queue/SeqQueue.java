package com.colson.dal.util.queue;

import com.colson.dal.util.constant.IndexConstant;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 顺序队列（先进先出）
 * 队首为下标为0的位置
 * @author songbowen
 * @param <E>
 */
public class SeqQueue<E> implements Serializable {

    transient E[] datas;

    public SeqQueue() {
    }

    /**
     * 获取长度
     */
    public int size() {
        return datas==null?0:datas.length;
    }

    /**
     * 向队头插入元素
     * @param data
     */
    public void add(E data) {
        if (null == datas) {
            datas = (E[]) new Object[1];
            datas[0] = data;
        } else {
            E[] es = Arrays.copyOf(datas, datas.length + 1);
            for (int i=es.length-1;i>0;i--) {
                E temp = es[i];
                es[i] = es[i-1];
                es[i-1] = temp;
            }
            es[0] = data;
            datas = es;
        }
    }

    /**
     * 移除队尾元素
     */
    public E del() {
        if (null==datas || datas.length==0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(IndexConstant.ZERO));
        }
        E result = datas[datas.length-1];
        datas = Arrays.copyOf(datas, datas.length - 1);
        return result;

    }

    /**
     * 获取指定下标的数据
     * @param index
     * @return
     */
    public E get(int index) {
        if (null==datas || datas.length==0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        return datas[index];
    }

    /**
     * 返回指定数据的下标
     * @param data
     * @return
     */
    public int indexOf(E data) {
        if (null==datas || datas.length==0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(IndexConstant.ZERO));
        }
        for (int i=0;i<datas.length;i++) {
            if (get(i).equals(data)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        if (null == datas) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<datas.length;i++) {
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
}
