package com.my.pro;

import java.util.Arrays;

public class NumberOfDiscIntersections {

    public static void main(String[] args) {

        int[] arr = {1, 5, 2, 1, 4, 0};
        int i = solution(arr);
    }


    public static int solution(int[] arr) {

        int[] leftEdges = new int[arr.length];
        int[] rightEdges = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            leftEdges[i] = i - arr[i];
            rightEdges[i] = i + arr[i];
        }

        Arrays.sort(leftEdges);
        Arrays.sort(rightEdges);

        int intersectionCount = 0;
        int j = 0;

        /*
         *          -4   -3   -2   -1   0   1   2   3   4   5   6    7    8
         *    0                     *-------*
         *    1      *------------------------------------------*
         *    2                         *---------------*                             
         *    3                                 *-------*
         *    4                         *---------------------------------*
         *    5                                             *
         *
         */

        //   left -4, -1, 0, 0, 2, 5      right 1, 4, 4, 5, 6, 8

        for (int i = 0; i < arr.length - 1; i++) {

            while (j < arr.length && rightEdges[i] >= leftEdges[j]) {
                intersectionCount += j - i;
                j++;

                if (intersectionCount > 10_000_000) return -1;
            }

        }


        return intersectionCount;
    }
}