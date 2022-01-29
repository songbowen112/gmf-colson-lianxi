package com.colson.demo.queue;


import com.colson.util.queue.SeqQueue;
import com.colson.util.queue.SeqQueue2;
import com.colson.util.stack.SeqStack;

/**
 * Q是一个队列,S是一个空栈，实现队列中的元素逆置的算法
 */
public class QueueDemo {

    public static void main(String[] args) {
//        SeqQueue<String> queue = new SeqQueue();
        SeqQueue2<String> queue = new SeqQueue2();
        queue.add("虎");
        queue.add("老");
        queue.add("打");
        queue.add("山");
        queue.add("上");
        System.out.println(queue);

        SeqStack<String> stack = new SeqStack();
        for (int i=0;i<queue.size();i++) {
            stack.push(queue.find(i));
        }
        System.out.println(stack);

        SeqQueue<String> queue1 = new SeqQueue();
        for (int i=0;i<queue.size();i++) {
            queue1.add(stack.pop());
        }
        System.out.println("逆置后的结果为："+queue1);
    }
}
