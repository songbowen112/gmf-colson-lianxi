package com.colson.dal.stack;

import com.colson.dal.util.SeqStack;

public class SeqStackTest {
    public static void main(String[] args) {
        SeqStack stack = new SeqStack();
        System.out.println(stack);
        stack.push(12);
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println("此栈长度为："+stack.size());

        stack.push(5);
        stack.push(4);
        stack.push(5);
        stack.push(2);
        stack.push(6);
        stack.push(3);
        System.out.println(stack);
        System.out.println("此栈长度为："+stack.size());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println(stack);

    }
}
