package com.my.pro;

import java.util.Arrays;

public class MedianTwoSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2, 7};

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};

        double medianSortedArrays1 = findMedianSortedArrays(nums1, nums2);
        double medianSortedArrays2 = findMedianSortedArrays(nums3, nums4);
    }

    /*
     *
     *  nums1[max] < nums2[min]
     *
     *  nums1[min] > nums2[max]
     *
     *  nums1[min]<nums2[min] && nums2[max]<nums1[max] arr2 inside arr1
     *
     *  nums2[min]<nums1[min] && nums1[max]<nums2[max] arr1 inside arr2
     *
     */

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0) return getMedian(nums2);
        if (nums2.length == 0) return getMedian(nums1);

        int min1 = nums1[0], max1 = nums1[nums1.length - 1];
        int min2 = nums2[0], max2 = nums2[nums2.length - 1];
        int[] arr;

        if ((min1 < min2) && (max1 < min2)) {
            arr = createOneArray(nums1, nums2, false);
            return getMedian(arr);
        }

        if ((min1 > min2) && (min1 > max2)) {
            arr = createOneArray(nums2, nums1, false);
            return getMedian(arr);
        }

        arr = createOneArray(nums1, nums2, true);
        return getMedian(arr);
    }

    private static double getMedian(int[] arr) {
        int length = arr.length;
        int mid = length / 2;

        if (length % 2 == 1) {
            return arr[mid];
        } else {
            return (arr[mid - 1] + arr[mid]) / 2.0;
        }
    }

    private static int[] createOneArray(int[] arr1, int[] arr2, boolean shouldSort) {
        int[] seqArray = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, seqArray, 0, arr1.length);
        System.arraycopy(arr2, 0, seqArray, arr1.length, arr2.length);
        if (shouldSort) Arrays.sort(seqArray);
        return seqArray;
    }
}