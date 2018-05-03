package com.leetcode.linkedlist;

import com.leetcode.util.InputFactory;

public class LInkedList {

    public static void main(String args[]) {

        Node node = createlinkList(InputFactory.getArray());

        //System.out.println(node);
        printList(node);
//        Node head = reverseInGroup(node,2);
  //      printList(head);

        Node loop1 = createLoop(node);
        Node loop = detectLoop(loop1);
        System.out.println(loop.data);

        Node n = RotateList(node , 2);
        printList(n);
    }

    public static Node createLoop(Node node) {
        Node head = node;
        while(node.next != null) {
            node = node.next ;
        }
        node.next = head.next.next;
        return head;
    }

    public static void removeLoop(Node loop , Node curr) {
        Node ptr1 = null, ptr2 = null;
        ptr1 = curr;
        while(true) {
            ptr2 = loop;
            while(ptr2.next.data != loop.data && ptr2.next.data != ptr1.data) {
                ptr2 = ptr2.next;
            }
            if(ptr2.next.data  == ptr1.data) {
                break;
            }
            ptr1 = ptr1.next;
        }

        ptr2.next = null;
    }

    public static Node detectLoop(Node node) {
        Node slow = node , fast = node;

        while(slow!= null && slow.next != null && fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow.data == fast.data) {
                removeLoop(slow , node);
                return node;
            }
        }
        return null;
    }

    public static Node reverseList(Node node) {
        Node newhead = null;
        Node temp = node;
        while(temp!= null) {
            Node next = temp.next;
            temp.next = newhead;
            newhead = temp;
            temp = next;
        }
        return newhead;
    }

    public static Node reverseInGroup(Node head ,int size) {

        Node current = head;
        Node next = null;
        Node prev = null;
        int count =0;

        while(count < size && current!= null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        if(next != null) {
            head.next = reverseInGroup(next, size);
        }
        return prev;
    }

    public static void printList(Node head) {
        System.out.println("");
        Node temp = head;
        while(temp != null) {
            System.out.print("->" + temp.data);
            temp = temp.next;
        }
    }

    public static Node createlinkList(int arr[]) {
        Node head = null;
        Node temp = null;

        for(int i=0; i< arr.length ; i++) {
            if(head == null) {
                head = new Node(arr[i]);
                temp = head;
            } else {
                Node next = new Node(arr[i]);
                temp.next = next;
                temp = temp.next;
            }
        }
        return head;
    }

    public static Node RotateList(Node node ,  int n) {
        Node head = node;
        Node temp = node;
        int count = 0;
        while (count != n && temp != null) {
            temp = temp.next;
            count++;
        }

        while (temp.next != null) {
            temp = temp.next;
            head = head.next;
        }

        temp.next = node;
        node = head;
        temp = null;
        return node;
    }
}
