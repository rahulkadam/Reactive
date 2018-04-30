package com.leetcode.backtracking;

import java.util.HashSet;
import java.util.Set;

public class permutation {

    static Set<String> set = new HashSet<>();
    public static void main(String args[]) {
        String str = "ABCA";
        permutation(str, 0, str.length() - 1);
    }

    /**
     * FUnction to make swap for element ,m then recursion for finding next and then again revert swap
     * @param str
     * @param l
     * @param h
     */
    public static void permutation(String str, int l, int h) {
        if (l == h) {
            if(!set.contains(str)) {
                System.out.println(str);
                set.add(str);
            }
            return;
        }

        for (int i = l; i < str.length(); i++) {
            str = swap(str, l, i);
            permutation(str, l + 1, h);
            str = swap(str, i, l);
        }
    }

    public static String swap(String str, int i, int j) {
        char[] c = str.toCharArray();
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
        str = String.valueOf(c);
        return str;
    }
}
