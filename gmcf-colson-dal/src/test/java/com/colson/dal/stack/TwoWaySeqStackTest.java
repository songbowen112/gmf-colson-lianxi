package com.colson.dal.stack;

import com.colson.dal.util.stack.TwoWaySeqStack;

public class TwoWaySeqStackTest {

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
