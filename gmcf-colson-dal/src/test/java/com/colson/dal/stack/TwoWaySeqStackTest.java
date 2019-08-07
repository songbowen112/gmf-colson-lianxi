package com.colson.dal.stack;

import com.colson.dal.util.stack.TwoWaySeqStack;

public class TwoWaySeqStackTest {

    public static void main(String[] args) {
        TwoWaySeqStack stack = new TwoWaySeqStack();
        System.out.println(stack);
        stack.pushLeft(1);
        stack.pushLeft(2);
        stack.pushLeft(1);
        stack.pushLeft(1);
        stack.pushLeft(1);
        stack.pushLeft(1);

        stack.pushRight(3);
        stack.pushRight(6);
        System.out.println(stack);
        System.out.println(stack.getLeftSize());
        System.out.println(stack.getRightSize());

    }
}
