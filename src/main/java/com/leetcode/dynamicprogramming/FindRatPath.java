package com.leetcode.dynamicprogramming;

import com.leetcode.util.InputFactory;

public class FindRatPath {

    public static void main(String args[]) {
        System.out.println(findPathDP(InputFactory.getMatrix()));
        System.out.println(pathRecursion(InputFactory.getMatrix()));
    }

    public static int findPathDP(int[][] arr) {
        int m = InputFactory.getRowCount(arr);
        int n = InputFactory.getColCount(arr);
        int[][] result = new int[m][n];
        int i = 1;
        result[0][0] = 1;
        while (i < m && arr[i][0] != 0) {
            result[i][0] = 1;
            i++;
        }

        i = 1;
        while (i < m && arr[0][i] != 0) {
            result[0][i] = 1;
            i++;
        }
        InputFactory.printMatrix(result);

        for (i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (arr[i][j] == 1) {
                    result[i][j] = result[i][j - 1] + result[i - 1][j];
                } else {
                    result[i][j] = 0;
                }
            }
        }
        InputFactory.printMatrix(result);
        return result[m - 1][n - 1];
    }


    public static int pathRecursion(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int maxpathcount = findPath(arr, m, n, 0, 0);
        return maxpathcount;
    }

    static int findPath(int[][] arr, int m, int n, int currentx, int currenty) {

        if (currentx == m || currenty == n) {
            return 0;
        }

        if (currentx == m - 1 && currenty == n - 1) {
            return 1;
        }

        if (arr[currentx][currenty] == 0) {
            return 0;
        }
        return findPath(arr, m, n, currentx + 1, currenty) + findPath(arr, m, n, currentx, currenty + 1);

    }
}
