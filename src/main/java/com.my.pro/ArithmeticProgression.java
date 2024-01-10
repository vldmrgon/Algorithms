package com.my.pro;

public class ArithmeticProgression {

    public static void main(String[] args) {

        int[] a = {1, 3, 2, 5};

        int number = lostNumber(a);

    }

    /*
     *     N * ( N + 1 ) / 2           Arithmetic progression
     */

    public static int lostNumber(int[] arr) {
        int n = arr.length + 1;
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int i : arr) actualSum += i;
        return expectedSum - actualSum;
    }
}