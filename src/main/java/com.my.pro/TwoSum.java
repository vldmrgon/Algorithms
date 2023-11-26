package com.my.pro;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {

        int[] ex1 = twoSum(new int[]{12, 7, 11, 15}, 9);
        int[] ex2 = twoSum(new int[]{3, 2, 4}, 6);
        int[] ex3 = twoSum(new int[]{-3,4,3,90}, 0);

        System.out.println();
    }

    /*
     *
     *  0   1    2    3
     * [2] [7] [11] [15]
     *
     * Map<Number, Index>
     *
     */

    private static int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int number = arr[i];
            if (number > target) continue;

            int diff = target - number;

            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
            map.put(arr[i], i);
        }
        return null;
    }
}