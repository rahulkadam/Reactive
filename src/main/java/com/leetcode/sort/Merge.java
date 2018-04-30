package com.leetcode.sort;

import com.leetcode.util.InputFactory;

public class Merge {
    public static void main(String args[]) {
        // 1, 5, 3, 6, 7
        int arr[] = InputFactory.getArray();

        mergeSort(arr, 0, arr.length - 1);
        InputFactory.printArray(arr);
    }

    public static int[] mergeSort(int[] arr, int low, int high) {

        if (low < high) {
            int m = (high + low) / 2;
            mergeSort(arr, low, m);
            mergeSort(arr, m + 1, high);
            merge(arr, low, m, high);
        }
        return arr;
    }

    public static void merge(int[] arr, int l, int m, int h) {

        int n1 = m - l + 1;
        int n2 = h - m;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for(int i=0; i < n1 ; ++i) {
            L[i] = arr[l+i];
        }
        for(int i=0; i < n2 ; ++i) {
            R[i] = arr[m+1+i];
        }

        int i=0 , j=0;
        int k =l;
        while(i < n1 && j < n2) {
            if(L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
