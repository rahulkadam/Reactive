package com.leetcode.greedy;


/**
 * TODO Greedy algo help to find solution by step by step.
 * we check and execute optimal solution every time
 */
public class ActivitySelection {
    public static void main(String args[]) {
        int s[] =  {10, 12, 20};
        int f[] =  {20, 25, 30};
        int n = s.length;

        System.out.println(printMaxActivities(s, f, n));
    }

    public static int printMaxActivities(int arr1[] , int arr2[] , int n) {
        int currentStart = 0;
        int currentfinish = 0;
        int count = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] >= currentStart && currentfinish <= arr1[i]) {
                count++;
                currentStart = arr1[i];
                currentfinish = arr2[i];
            }
        }
        return count;
    }
}
