package com.leetcode.dynamicprogramming;


import com.leetcode.util.InputFactory;

/**
 * Problem is to find longest common sequence from 2 string ,
 * We can solve this problem by 2 ways
 * 1. Recursion ( THis will use backtracking to check and try for each and every combination) exponential complexity
 * 2. Dynamic Programming ( with this approcah we will check every combination using matri form,. create matrix [m+1][n+1] and try to match from 1 to 1 than 0.
 */
public class LongestCommonSubsequence {
    public static void main(String args[]) {
        String str1 = "ADFDB";
        String str2 = "AFDB";
        System.out.println(LSCRecursive(str1,str2));
        System.out.println(LSCDP(str1,str2));
    }

    public static int LSCDP(String str1 , String str2) {
        int l1 = str1.length();
        int l2 = str2.length();
        int[][] result = new int[l1][l2];

        for(int i=0; i < l1 ; i++) {
            if(str2.charAt(0) == str1.charAt(i)) {
                result[0][i] = 1;
            }
        }
        for(int i=0; i < l2 ; i++) {
            if(str1.charAt(0) == str2.charAt(i)) {
                result[i][0] = 1;
            }
        }

        for(int i=1; i <l1 ; i++) {
            for(int j =1; j< l2  ; j++) {
                if(str1.charAt(i) == str2.charAt(j)) {
                    result[i][j] = 1 + result[i-1][j-1];
                } else {
                    result[i][j] = InputFactory.max(result[i-1][j] ,result[i][j-1]);
                }
            }
        }
        InputFactory.printMatrix(result);
        return result[l1-1][l2-1];
    }

    public static int LSCRecursive(String str1 , String str2) {
        return MaxLSC(str1 , str2 , 0,0);
    }

    public static int MaxLSC(String str1 , String str2 , int n , int m) {
        if(str1.length() == n || m == str2.length()) {
            return 0;
        }
        if(str1.charAt(n) == str2.charAt(m)) {
            return 1 + MaxLSC(str1 ,str2 ,n+1 , m+1);
        } else {
            return InputFactory.max(MaxLSC(str1 ,str2 ,n , m+1) , MaxLSC(str1 ,str2 ,n+1 , m));
        }
    }
}
