package com.leetcode.patternsearching;

import com.leetcode.util.InputFactory;


/**
 * TODO KMP Algo
 */
public class SearchPattern {

    public static void main(String args[]) {
        String str = "AABAACAADAABAABA";
        String p = "AABA";
        int a[] = pattern(str,p);
        InputFactory.printArray(a);
    }

    public static int[] pattern(String str , String p) {
        int[] arr = new int[10];
        int index = 0;

        for(int i=0; i< str.length()-p.length()+1 ; i++) {
            int tmp = i;
            boolean b = true;
            for(int j=0; j < p.length() ; j++) {
                if(str.charAt(tmp) != p.charAt(j)) {
                    b = false;
                }
                tmp++;
            }
            if(b) {
                arr[index] = i;
                index++;
            }
        }

        return arr;
    }
}
