package com.my.pro;

public class Caterpillar {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int maxSum = 8;

        int result = maxSumSubarray(array, maxSum);
        System.out.println("Max Sequence: " + result);
    }

    public static int maxSumSubarray(int[] A, int maxSum) {
        int n = A.length;
        int left = 0, right = 0;
        int currentSum = 0;

        while (right < n) {

            currentSum += A[right];

            while (currentSum > maxSum) {

                currentSum -= A[left];
                left++;
            }
            right++;
        }
        return currentSum;
    }
}