package com.my.pro;

public class Searching {

    public static void main(String[] args) {

        int[] arr = {2, 5, 8, 12, 16, 23, 38, 42, 50, 57};
        int index1 = binaryRecursiveSearch(arr, 12);
        int index2 = binaryWhileSearch(arr, 12);
    }

    private static int binaryRecursiveSearch(int[] arr, int number) {
        return binaryRecursiveSearch(arr, number, 0, arr.length - 1);
    }

    /*
     *     0  1  2   3   4   5   6   7   8   9
     * 1   2, 5, 8, 12, 16, 23, 38, 42, 50, 57      target 12
     * 2   2, 5, 8, 12
     * 3         8, 12
     * 4    return  12
     */

    private static int binaryRecursiveSearch(int[] arr, int target, int left, int right) {

        if (left <= right) {
            int middle = left + (right - left) / 2;

            if (target == arr[middle]) {
                return middle;
            }

            if (target < arr[middle]) {
                return binaryRecursiveSearch(arr, target, left, middle - 1);
            } else {
                return binaryRecursiveSearch(arr, target, middle + 1, right);
            }
        }
        return -1;
    }

    private static int binaryWhileSearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == arr[mid]) return mid;
            if (target < arr[mid]) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}