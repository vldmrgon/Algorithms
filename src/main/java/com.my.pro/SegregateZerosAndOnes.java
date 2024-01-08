package com.my.pro;

import java.util.Arrays;

public class SegregateZerosAndOnes {

    public static void main(String[] args) {
        int[] array = {0, 1, 0, 1, 0, 1, 0, 1};

        System.out.println("Original Array: " + Arrays.toString(array));
        segregate(array);
        System.out.println("Segregated Array: " + Arrays.toString(array));
    }

    public static void segregate(int[] arr) {

        int iter = arr.length / 2;

        for (int i = 0; i < iter; i++) {
            int left = i;
            int right = arr.length - 1 - i;

            if (arr[left] > arr[right]) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
    }
}