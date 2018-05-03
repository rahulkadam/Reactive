package com.leetcode.linkedlist;

public class Node {
    public int data;
    public Node next;

    public Node() {
        next = null;
    }

    public Node(int data) {
        next = null;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
