package com.leetcode.dynamicprogramming;

import com.leetcode.util.InputFactory;

public class LongestpalindromicSequence {

    public static void main(String args[]) {
        String str1 = "GAG";
        System.out.println(LPSRecursive(str1 , 0, str1.length()-1));
        System.out.println(LPSDP(str1));
    }

    public static int LPSRecursive(String str , int l , int h) {
        if( l == h) {
            return 1;
        }
        if(str.charAt(l) == str.charAt(h) &&  l+1 == h) {
            return 2;
        }
        if(str.charAt(l) == str.charAt(h)) {
            return LPSRecursive(str , l+1 , h-1) + 2;
        }
        return InputFactory.max(LPSRecursive(str , l , h-1) ,LPSRecursive(str , l+1 , h));
    }


    /**
     * Solving this problem using dynamic programming
     * putting and maging matrix only upper part of matrix
     * @param str
     * @return
     */

    public static int LPSDP(String str) {
        int n = str.length();
        int result[][] = new int[n][n];
        int i,j,cl;

        for(i=0; i <n; i++) {
            result[i][i] = 1;
        }

        for(cl=2 ; cl <= n ; cl++) {

            for(i=0; i < n-cl+1 ; i++) {
                j = i + cl-1;
                if(str.charAt(i) == str.charAt(j) && cl==2) {
                    result[i][j] = 2;
                } else if( str.charAt(i) == str.charAt(j)){
                    result[i][j] = result[i+1][j-1] + 2;
                } else {
                    result[i][j] = InputFactory.max(result[i][j-1] , result[i+1][j]);
                }
            }
            InputFactory.printMatrix(result);
        }
        return result[0][n-1];
    }
}
