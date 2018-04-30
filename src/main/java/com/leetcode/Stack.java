package com.leetcode;

public class Stack {

    static int size = 20;
    static int data[] = new int[size];
    static int top = 0;

    public static void main(String args[]) {

        push(4);
        push(5);
        push(6);
        printStack();
        pop();
        pop();
        pop();
        pop();
        printStack();
        push(8);
        printStack();
    }

    static void printStack() {
        System.out.print("Top : " + top + "    values : ");
        for(int i=0; i < top ; i++) {
            System.out.print("->"+data[i]);
        }
        System.out.println("");
    }
    static void push(int n) {
        if (top < size) {
            data[top] = n;
            top++;
        } else {
            System.out.println("stack is full. can not push element" + n);
        }
    }

    static boolean isEmpty() {
        return top == 0;
    }

    static int pop() {
        if (!isEmpty()) {
            return data[top--];
        } else {
            System.out.println("empty stack");
            return Integer.MIN_VALUE;
        }
    }

    static int top() {
        return data[top];
    }
}
