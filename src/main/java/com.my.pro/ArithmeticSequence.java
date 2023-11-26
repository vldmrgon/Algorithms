package com.my.pro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArithmeticSequence {

    public static void main(String[] args) {

        int[] nums = {1, 2, 10, -6, -7, 8, 16, 0, 0, 10, 20, 15, -2, -3, -1, -4, -4, -8, -2};
        int[] l = {5, 1, 6, 4, 8, 7};
        int[] r = {8, 4, 9, 7, 9, 10};

        List<Boolean> booleans = checkArithmeticSubarrays(nums, l, r);
    }

    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {

        List<Boolean> list = new ArrayList<>();

        if (l.length != r.length) throw new RuntimeException("Wrong length subarrays");

        for (int i = 0; i < l.length; i++) {
            int[] subArray = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            Arrays.sort(subArray);
            list.add(checkOrder(subArray));
        }
        return list;
    }

    private static boolean checkOrder(int[] subArray) {
        int step = 0;

        for (int j = 0; j < subArray.length - 1; j++) {

            boolean b = zeroArray(subArray);
            if (b) return true;

            int current = subArray[j];
            int next = subArray[j + 1];

            if (current == next ) return false;
            int currentStep = Math.abs(current - next);

            if (step == 0) step = currentStep;
            if (step != currentStep) return false;
        }
        return true;
    }

    private static boolean zeroArray(int[] subArray) {
        for (int i : subArray) {
            if (i != 0) return false;
        }
        return true;
    }
}