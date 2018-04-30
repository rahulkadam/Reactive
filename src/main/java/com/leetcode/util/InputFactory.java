package com.leetcode.util;

public class InputFactory {

    public static int[] getArray() {
        int[] array = {1, 5, 3, 6, 7};
        return array;
    }

    public static int[][] getZeroOneMatrix() {
        int[][] array = {{1, 1, 1, 0},
                {1, 1, 1, 0},
                {1, 0, 1, 0},
                {1, 1, 1, 1}};
        return array;
    }

    public static int[][] getMatrix() {
        int[][] array = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1}};
        return array;
    }

    public static void printArray(int[] arr) {
        System.out.println("");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" -> " + arr[i]);
        }
        System.out.println("");
    }

    public static int getRowCount(int[][] arr) {
        return arr.length;
    }

    public static int getColCount(int[][] arr) {
        return arr[0].length;
    }

    public static void printMatrix(int[][] arr) {
        System.out.println("");
        for (int i = 0; i < getRowCount(arr); i++) {
            System.out.println("");
            for (int j = 0; j < getColCount(arr); j++) {
                System.out.print(" -> " + arr[i][j]);
            }
        }
        System.out.println("");
    }

    static int[][] copyMatrix(int[][] arr) {
        int m = getRowCount(arr);
        int n = getColCount(arr);
        int[][] copy = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    public static int max(int a, int b) {
        if (a > b)
            return a;
        return b;
    }

    public static int min(int a, int b) {
        if (a < b)
            return a;
        return b;
    }
}
