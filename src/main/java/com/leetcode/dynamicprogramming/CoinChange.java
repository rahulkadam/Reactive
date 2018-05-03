package com.leetcode.dynamicprogramming;

import com.leetcode.util.InputFactory;

public class CoinChange {
    public static void main(String args[]) {
        int arr[] = {1,2,3};
        int sum = 4;
        System.out.println(coinChange(arr,arr.length , sum));
        System.out.println(coinChangeDP(arr , arr.length ,sum));
    }

    public static int coinChange(int arr[] , int m , int n) {
        if(n==0) {
            return 1;
        }

        if(n < 0) {
            return 0;
        }

        if(m <= 0 && n >=1) {
            return 0;
        }
        return coinChange(arr,m-1,n) + coinChange(arr,m,n - arr[m-1]);
    }

    public static int coinChangeDP(int arr[] , int m , int n) {

        int table[] = new int[n+1];

        for(int i=0;i< table.length ;i++) {
            table[i] = 0;
        }

        table[0] = 1;

        for(int i=0; i < m ; i++) {
            for(int j=arr[i] ; j <=n ; j++) {
                table[j] = table[j] + table[j-arr[i]];
                InputFactory.printArray(table);
            }
        }
        return table[n];
    }
}
