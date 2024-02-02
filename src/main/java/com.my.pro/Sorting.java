package com.my.pro;

import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {

        int[] trArr1 = {10, 2, 5, 1, 8, 20};
        int triplet1 = triangle(trArr1);
        int[] trArr2 = {10, 50, 5, 1};
        int triplet2 = triangle(trArr2);

        int[] array = {38, 27, 43, 3, 9, 82, 10};
        mergeSort(array);

        int[] arrHeap = {12, 4, 5, 6, 7, 8, 1, 15, 3, 10};
        heapSort(arrHeap);

        int[] arrBubble = {20, 34, 12, 56, 33, 13, 9, 6, 7};
        bubbleSort(arrBubble);

        int[] arrQuick = {8, 2, 4, 7, 1, 3, 9, 6, 5};
        quickSort(arrQuick);

        int[] arrInsert = {5, 2, 9, 1, 5, 6};
        insertionSort(arrInsert);
    }

    /*
     *      0   1  2  3  4   5
     *      10, 2, 5, 1, 8, 20
     *       1  2  5  8  10 20
     *
     *   A[P] + A[Q] > A[R],
     *   A[Q] + A[R] > A[P],
     *   A[R] + A[P] > A[Q].
     *
     */
    public static int triangle(int[] arr) {
        if (arr.length < 3) return -1;
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            if (
                    (arr[i] + arr[i + 1] > arr[i + 2])
                            && (arr[i + 1] + arr[i + 2] > arr[i])
                            && (arr[i + 2] + arr[i] > arr[i + 1])
            ) {
                return 1;
            }
        }
        return 0;
    }

    public static void mergeSort(int[] arr) {
        int n = arr.length;

        if (n > 1) {
            int mid = n / 2;

            int[] left = new int[mid];
            int[] right = new int[n - mid];

            System.arraycopy(arr, 0, left, 0, mid);
            System.arraycopy(arr, mid, right, 0, n - mid);

            mergeSort(left);
            mergeSort(right);
            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }


        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    private static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                j--;
            }
        }
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        buildMaxHeap(arr);

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            n--;
            heapify(arr, n, 0);
        }
    }

    /*
     *
     *     i =
     *     left = 2 * i + 1
     *     right = 2 * i + 2
     *
     *     0   1  2  3   4  5  6  7  8  9
     *   [15, 12, 8, 6, 10, 5, 1, 4, 3, 7]
     *                   15
     *                 /    \
     *              12      8
     *            /    \    / \
     *           6     10  5   1
     *         /   \  /
     *        4    3 7
     *
     */
    public static void buildMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        if (rightChild < n && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    /*
     *  [i][j]                    pivot
     *  -1  0  1  2  3  4  5  6  7  8
     *
     *      8  2  4  7  1  3  9  6  5
     * 1   [2  8] 4  7  1  3  9  6  5
     * 2    2 [4  8] 7  1  3  9  6  5
     * 3    2  4 [1] 7 [8] 3  9  6  5
     * 4    2  4  1 [3] 8 [7] 9  6  5
     * 5    2  4  1  3 [5] 7  9  6 [8]
     *
     *     left - pivot-1       pivot+1 - right
     * 6     2 4 1 3     <- 5 ->    7 9 6 8
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr) {

        int n = arr.length - 1;
        boolean sorted;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            sorted = true;

            for (int j = 0; j < n; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) return;
        }
    }
}