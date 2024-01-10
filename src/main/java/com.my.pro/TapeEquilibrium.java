package com.my.pro;

public class TapeEquilibrium {

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4, 3};

        int equilibrium = equilibrium(arr);
    }

    /*
    *        leftArr        rightArr
    *  1)       3 = 3       1,2,4,3 = 10     7
    *  2)     3+1 = 4         2,4,3 = 9      5
    *  3)   3+1+2 = 6           4,3 = 7      1
    *  4) 3+1+2+4 = 10            3 = 3      7
    *
    *
    */

    public static int equilibrium(int[] arr) {

        int sum = 0;
        for (int i : arr) sum += i;

        int leftArr = arr[0], rightArr = sum - arr[0];
        int minDifference = Math.abs(rightArr - leftArr);

        for (int i = 1; i < arr.length; i++) {

            int currentDifference = (rightArr - arr[i]) - (leftArr + arr[i]);
            minDifference = Math.min(Math.abs(currentDifference), minDifference);
        }
        return minDifference;
    }
}