package com.my.pro;

public class FrogJmp {


    public static void main(String[] args) {
        int x = 26, y = 85, d = 30;

        int i = jumpsCounter(x, y, d);
    }

    /*
     *         1) 85/30 = 2.83 -> 3 -> 90 + 10 = 100
     *         2) 85/30 = 2.83 -> 2 -> 60 + 10 = 70
     *
     *
     */

    public static int jumpsCounter(int x, int y, int d) {

        int dividedNumber = y / d;

        int numMin = (dividedNumber * d) + x;
        int numMax = ((dividedNumber + 1) * d) + x;

        if (numMin >= y) return dividedNumber;
        if (numMax >= y) return dividedNumber + 1;
        return 0;
    }
}