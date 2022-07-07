package com.colson.util.skiplist;

import com.colson.util.queue.LinkedQueue;

/**
 * @author song
 * @description: 跳表
 * @date 2022/7/1 下午4:44
 */
public class SkipList<E> {

    //当前层数
    private int curLevel;

    //头结点，不保存值
    private Node head;

    //跳表中元素个数
    private int size;

    //用于生成随机层数
    private static final double PROBABILITY = 0.5;

    //最大层数,也可以写成通过构造函数注入的方式动态设置
    private static final int maxLevel = 8;

    public SkipList() {
        size = 0;
        curLevel = 0;
        head = new Node(null);
    }

    public int size() {
        return size;
    }



    private static class Node<E>{
        E data;

        private Node(E data) {
            this.data = data;
        }
    }
}
