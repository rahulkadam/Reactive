package com.leetcode.greedy;

public class MinimumProductSubset {
    public static void main(String args[]) {
        int arr[] = {-1, -1, -2, 4, 3 , 0};
        int arr1[] = {4, 3 , 1,12 , -2 , 0};
        int arr2[] = {-50, -50 , -1 , 0};
        System.out.println(product(arr));
        System.out.println(product(arr1));
        System.out.println(product(arr2));
    }

    public static int product(int arr[]) {

        int negativeCount=0;
        boolean isZeroPresent = false;
        int largeNegativeNumber = -99999;
        int minPositiveNumber = 99999;


        for(int i=0; i < arr.length ; i++) {
            if(arr[i] < 0) {
                negativeCount++;
                if(largeNegativeNumber < arr[i]) {
                    largeNegativeNumber = arr[i];
                }

            } else if(arr[i] == 0) {
                isZeroPresent = true;
            } else {
                if(minPositiveNumber > arr[i]) {
                    minPositiveNumber = arr[i];
                }
            }
        }

        int product = 1;
        if(negativeCount %2 == 1) {
            for(int i=0; i < arr.length ; i++) {
                if(arr[i] != 0) {
                    product = product * arr[i];
                }
            }
            return product;
        }

        if(negativeCount > 0 && negativeCount %2 == 0) {
            for(int i=0; i < arr.length ; i++) {
                if(arr[i] != 0 && arr[i] != largeNegativeNumber) {
                    product = product * arr[i];
                }
            }
            return product;
        }

        if (isZeroPresent) {
            return 0;
        }
        return minPositiveNumber;
    }

}
