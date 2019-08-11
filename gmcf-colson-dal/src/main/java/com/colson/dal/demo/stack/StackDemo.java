package com.colson.dal.demo.stack;

import com.colson.dal.util.stack.TwoWaySeqStack;

/**
 * 设有两个栈s1，s2都采用顺序栈方式，并共享一个存储区[0,...,maxsize-1]，为了尽量利用空间，减少溢出的可能，
 * 可采用栈顶相向、迎面增长的存储方式。设计出入栈操作算法。
 */
public class StackDemo {

    public static void main(String[] args) {
        TwoWaySeqStack stack = new TwoWaySeqStack();
        System.out.println(stack);
        stack.pushLeft(1);
        stack.pushLeft(2);
        stack.pushLeft(4);
        stack.pushLeft(7);
        stack.pushLeft(8);
        stack.pushLeft(9);
        System.out.println(stack.popLeft());
        System.out.println(stack.popLeft());

        stack.pushRight(3);
        stack.pushRight(4);
        stack.pushRight(5);
        stack.pushRight(10);
        System.out.println(stack.popRight());
        System.out.println(stack);
        System.out.println("左栈的长度为"+stack.getLeftSize());
        System.out.println("右栈的长度为"+stack.getRightSize());

    }


}
