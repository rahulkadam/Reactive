package com.leetcode.search;

import java.util.HashMap;

public class BinarySearch {

    public static void main(String args[]) {
        int arr[] = {3,2,2,2};

        System.out.println(countMoves(arr));
       // System.out.println(numberOfPairs(arr , 14l));
        int number = -2;
        // Binary search using recursion
        //System.out.println(search(arr,0,arr.length-1 , number));
       // System.out.println(search(arr , number));
        //System.out.println(linearSearch(arr,number));
    }

    public static boolean search(int[] arr , int lower, int higher , int num) {
        if(lower > higher) {
            return false;
        }

        int mid = lower + (higher-lower)/2;
        if(arr[mid] == num) {
            return true;
        } else if(arr[mid] < num) {
            return search(arr,mid+1, higher , num);
        } else {
            return search(arr,lower,mid-1, num);
        }
     }

     public static boolean search(int [] arr , int num) {
        int higher = arr.length ,lower = 0;

        while(lower< higher) {
          int mid = (higher + lower)/2;
          if(arr[mid] == num) {
              return true;
          } else if(arr[mid] < num){
              lower = mid + 1;
          } else{
              higher = mid;
          }
        }
        return false;
     }


     public static boolean linearSearch(int [] arr , int num) {
        int length = arr.length;

        for(int i=0; i < length;i++) {
            if(arr[i] == num) {
                return true;
            }
        }
        return false;
     }

     /*
    static int numberOfPairs(int[] a, long k) {
        /*
         * Write your code here.

        HashMap<Long, Long> map = new HashMap<>();
        int uniquePairCount = 0;
        for (int i = 0; i < a.length; i++) {
            Long numberTocheck = Long.valueOf(a[i]);

            Long number = map.get(numberTocheck);
            if (number == null) {
                if (map.get(k - numberTocheck) == null) {
                    map.put(k - numberTocheck, numberTocheck);

                } else {
                    if (numberTocheck + number == k) {
                        map.put(numberTocheck, -1l);
                        uniquePairCount++;
                    }
                }
            }
            return uniquePairCount;
        }
    }*/



    static long countMoves(int[] numbers) {
        /*
         * Write your code here.
         */
        int length = numbers.length;
        int count = 0;
        boolean equal = false;
        while(!checkEqual(numbers)) {
            equal = true;
            count++;
            int findmax = 0;
            int index = 0;
            for(int i=0; i < length ; i++) {
                if(numbers[i] > findmax) {
                    findmax = numbers[i];
                    index = i;
                }
            }

            for(int i=0; i < length ; i++) {
                if( i != index) {
                    numbers[i]++;
                }
                if(i < length -1 && index != i+1 && numbers[i] != numbers[i+1]+1) {
                    equal = false;
                }
            }

        }
        return count;

    }

    static boolean checkEqual(int[] numbers) {
        for(int i=0; i < numbers.length-1 ; i++) {
            if(numbers[i] != numbers[i+1]) {
                return false;
            }
        }
        return true;
    }



    public static int path(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        int maxpathcount = findPath(arr ,m ,n ,0 , 0);
        return maxpathcount;
    }

    static int findPath(int[][] arr , int m,int n , int currentx , int currenty) {

        if(currentx == m || currenty == n) {
            return 0;
        }

        if(currentx == m-1 && currenty == n-1) {
            return 1;
        }

        if(arr[currentx][currenty] ==0) {
            return 0;
        }
        return findPath(arr,m , n ,currentx +1 , currenty) +  findPath(arr,m , n, currentx  , currenty +1);

    }


}
