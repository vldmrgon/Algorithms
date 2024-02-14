package com.my.pro;

import java.util.*;

public class Matrix {

    public static void main(String[] args) {

        int[][] arr = {
                {1, 2, -1},
                {4, -1, 6},
                {7, 8, 9}
        };
        int[][] ints = modifiedMatrix(arr);
    }

    /*
     *     1  2 -1     1  2  9
     *     4 -1  6  -> 4  8  6   Change on max integer in the column
     *     7  8  9     7  8  9
     *
     *     i - string
     *     j - column
     * */

    public static int[][] modifiedMatrix(int[][] matrix) {
        Deque<Integer> stack = new ArrayDeque<>(matrix.length);

        for (int i = 0; i < matrix[0].length; i++) {
            int maxInteger = Integer.MIN_VALUE;

            for (int j = 0; j < matrix.length; j++) {
                int currentInteger = matrix[j][i];

                if (currentInteger == -1) {
                    stack.push(j);
                } else {
                    maxInteger = Math.max(maxInteger, currentInteger);
                }
            }

            while (!stack.isEmpty()) {
                matrix[stack.pop()][i] = maxInteger;
            }
        }
        return matrix;
    }
}