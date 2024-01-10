package com.my.pro;

import java.util.HashSet;
import java.util.Set;

public class FrogRiverOne {

    public static void main(String[] args) {

        int x = 5;
        int[] arr = {1, 3, 1, 4, 2, 3, 5, 4};
        int compute = compute(x, arr);
    }

    /* position
    *
    *     5             *
    *     4       *
    *     3   *       *
    *     2         *
    *     1 *   *
    *       0 1 2 3 4 5 6  X (time)
    *
    */

    public static int compute(int x, int[] arr) {
        Set<Integer> positions = new HashSet<>();
        int time = -1;

        for (int i = 0; i < arr.length; i++) {
            positions.add(arr[i]);

            if (positions.size() == x) {
                time = i;
                System.out.println("Frog jumps to another riverside: " + time);
                break;
            }
        }
        return time;
    }
}