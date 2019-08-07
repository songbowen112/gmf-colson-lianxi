package com.colson.dal.util.stack;

import com.colson.dal.util.constant.StackType;

import java.io.Serializable;

/**
 * 双向顺序栈
 */
public class TwoWaySeqStack<E> implements Serializable {

    /**
     * 共享存储区
     */
    private Object[] data = new Object[100];

    /**
     * 左栈顶下标
     */
    private int leftIndex = 0;

    /**
     * 右栈顶下标
     */
    private int rightIndex = data.length-1;

    public TwoWaySeqStack() {
    }

    /**
     * 入左栈
     * @param data
     */
    public void pushLeft(E data) {
        if (rightIndex-leftIndex == 1) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(leftIndex,StackType.LEFT_STACK));
        }
        this.data[leftIndex++] = data;
    }

    /**
     * 入右栈
     * @param data
     */
    public void pushRight(E data) {
        if (rightIndex-leftIndex == 1) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(rightIndex,StackType.RIGHT_STACK));
        }
        this.data[rightIndex--] = data;
    }

    /**
     * 出左栈
     * @return
     */
    public E popLeft() {
        if (leftIndex == 0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(leftIndex,StackType.LEFT_STACK));
        }
        return (E)data[--leftIndex];
    }

    /**
     * 出右栈
     * @return
     */
    public E popRight() {
        if (rightIndex == data.length-1) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(0,StackType.RIGHT_STACK));
        }
        return (E)data[++rightIndex];
    }

    public int getLeftSize() {
        return leftIndex;
    }

    public int getRightSize() {
        return data.length-1-rightIndex;
    }

    /**
     * 获取双向栈的占用大小
     */
    public int size() {
        return getLeftSize()+getRightSize();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("左栈: [");
        for (int i=leftIndex-1;i>=0;i--) {
            if (i == 0) {
                sb.append(data[i]);
            } else {
                sb.append(data[i]+",");
            }
        }
        sb.append("]\n右栈: [");
        for (int i=rightIndex+1;i<data.length;i++) {
            if (i == data.length-1) {
                sb.append(data[i]);
            } else {
                sb.append(data[i]+",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 下标越界信息
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index,int stackType) {
        if (StackType.LEFT_STACK == stackType) {
            return "Index: "+index+", Size: "+getLeftSize();
        }
        if (StackType.RIGHT_STACK == stackType) {
            return "Index: "+index+", Size: "+getRightSize();
        }
        return "";
    }
}
