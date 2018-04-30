package com.leetcode.sort;

import com.leetcode.util.InputFactory;

public class Selection {

    public static void main(String args[]) {

        InputFactory.printArray(sort(InputFactory.getArray()));
    }

    public static int[] sort(int arr[]) {
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            int min =999;
            int index = 0;
            for (int j = i; j < length; j++) {
                if (min > arr[j]) {
                     index = j;
                     min = arr[j];
                }
            }
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
