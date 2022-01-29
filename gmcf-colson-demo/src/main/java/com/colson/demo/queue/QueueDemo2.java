package com.colson.demo.queue;


import com.colson.util.stack.LinkedStack;

/**
 * 利用俩个栈S1,S2来模拟一个队列,利用栈的算法来实现队列的三个运算
 * 1.将元素x入队
 * 2.出队,并将出队元素x存入x中
 * 3.判断队列是否为空
 */
public class QueueDemo2 {

    public static void main(String[] args) {


    }

    class QueueOfStack {
        LinkedStack<String> stack1 = new LinkedStack<>();
        LinkedStack<String> stack2 = new LinkedStack<>();

    }
}
