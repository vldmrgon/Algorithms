package com.my.pro;

import java.util.Arrays;

public class MaxCounter {


    public static void main(String[] args) {

        int[] arr = new int[7];
        arr[0] = 3;
        arr[1] = 4;
        arr[2] = 4;
        arr[3] = 6;
        arr[4] = 1;
        arr[5] = 4;
        arr[6] = 4;

        int[] maxCounter = getMaxCounter(5, arr);
        System.out.println();
    }

//             (0, 0, 1, 0, 0)
//             (0, 0, 1, 1, 0)
//             (0, 0, 1, 2, 0)
//             (2, 2, 2, 2, 2)
//             (3, 2, 2, 2, 2)
//             (3, 2, 2, 3, 2)
//             (3, 2, 2, 4, 2)

    public static int[] getMaxCounter(int numberCounters, int[] arr) {

        int[] counters = new int[numberCounters];
        int maximum = 0;

        for (int indexCounter : arr) {

            if (indexCounter <= numberCounters) {
                int currentValue = counters[indexCounter-1] + 1;
                counters[indexCounter-1] = currentValue;
                maximum = Math.max(maximum, currentValue);
            } else {
                Arrays.fill(counters, maximum);
            }
        }
        return counters;
    }
}