package com.leetcode.sort;

import com.leetcode.util.InputFactory;

public class Quick {
    public static void main(String args[]) {
        // 1, 5, 3, 6, 7
        int arr[] = InputFactory.getArray();

        quickSort(arr, 0,arr.length-1);
        InputFactory.printArray(arr);
    }

    // 1, 5, 3, 6, 7
    //
    public static void quickSort(int[] arr , int low ,  int high) {

        if(low < high) {
            int pivot = pivot(arr, low , high);
            quickSort(arr, low , pivot-1);
            quickSort(arr, pivot+1 , high);
        }
    }

    /**
     * Function to find pivot element and while finding pivot element we sort/replace
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int pivot(int[] arr ,  int low , int high) {
        int pivot = arr[high];
        int i = low-1;
        for(int j=low ; j< high ;j++) {
            if(arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }


}

