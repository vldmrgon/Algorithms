package com.my.pro;

public class ArrayPipeline {

    public static void main(String[] args) {

        String message = "***|***|***|*|***|******|";
        int[] a = {0, 8, 8, 13, 17};
        int[] b = {4, 19, 10, 17, 24};
        int[] ints = countSub(message, a, b);
        System.out.println();
    }

    /*

      * * * | * * * | * *  *  |  * |   *  *  *  |  *  *  *  *  *  *  |
      0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24

     */

    public static int[] countSub(String msg, int[] a, int[] b) {

        int lengthArrays = a.length;
        int[] arr = new int[lengthArrays];

        for (int x = 0; x < lengthArrays; x++) {

            int numA = a[x];
            int numB = b[x];

            int countStars = 0;

            for (int y = numA + 1; y < numB; y++) {

                char ch = msg.charAt(y);

                if (ch == '|') {
                    countStars = 0;
                    break;
                } else if (ch == '*') {
                    countStars++;
                } else {
                    throw new RuntimeException("Wrong symbol: " + ch);
                }
            }
            arr[x] = countStars;
            countStars = 0;
        }
        return arr;
    }
}