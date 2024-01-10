package com.my.pro;

public class CyclicRotation {

    public static void main(String[] args) {

        int[] a = {3, 8, 9, 7, 6};       // [9, 7, 6, 3, 8]
        int[] rotate = rotate(a, 3);

    }

    /*
     *
     *  1) [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
     *  2) [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
     *  3) [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
     *
     */

    public static int[] rotate(int[] a, int k) {

        for (int i = 0; i < k; i++) {

            int swap = a[0];
            a[0] = a[a.length - 1];

            for (int j = 1; j < a.length ; j++) {
                int currentSwap = a[j];
                a[j] = swap;
                swap = currentSwap;
            }
        }
        return a;
    }
}