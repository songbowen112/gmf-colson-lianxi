package com.colson.dal.bean;


import java.util.LinkedList;

public class DoubleLinkList<E> {

    Node<E> first;

    Node<E> last;

    int size;

    Node node = new Node();

    private static class Node<E>{

        Node<E> prev;

        Node<E> next;

        E data;

        public Node() {
        }

        private Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }
}
