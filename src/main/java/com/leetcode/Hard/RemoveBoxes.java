package com.leetcode.Hard;

import com.leetcode.util.InputFactory;

import java.util.HashMap;

// TODO Incomplete. will need to solve this
public class RemoveBoxes {

    public static void main(String args[]) {
        int arr[] = InputFactory.getArray();
        InputFactory.printArray(arr);

    }

    public static int findScore(int[] arr) {

        int temp[] = arr;
        HashMap <Integer , Integer> countMap = new HashMap<>();

        for(int i=0; i < arr.length ; i++) {
            if(countMap.get(arr[i]) != null) {
                countMap.put(arr[i] , countMap.get(arr[i]) + 1);
            } else {
                countMap.put(arr[i] , 1);
            }
        }

        while(!countMap.isEmpty()) {

            int count = 0;
            for(int i=0; i < arr.length ; i++) {
                while(arr[i] == arr[i+1]) {
                }
            }
        }


        return 0;
    }
}
