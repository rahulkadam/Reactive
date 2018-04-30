package com.leetcode;

public class Matrix {

    public static void main(String args[]) {
        int m=5,n=2;
        int arr[][] = new int[m][n];

        printmatrix(arr , m,n);
    }

    public static void printmatrix(int arr[][],int m ,  int n) {
        for(int i=0; i < m;i++) {
            for(int j=0; j< n ; j++) {
                System.out.print(" : " + arr[i][j]);
            }
            System.out.println("");
        }
    }
}
