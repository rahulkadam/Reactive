package com.leetcode.sort;

import com.leetcode.util.InputFactory;

public class Bubble {

    public static void main(String args[]) {

        InputFactory.printArray(sort(InputFactory.getArray()));
    }

    public static int[] sort(int arr[]) {
        int length = arr.length;

        for(int i=0; i < length ; i++) {
            for(int j = 0; j < length-i ; j++) {
                if(arr[i] > arr[j]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                 }
            }
        }
        return arr;
    }
}
