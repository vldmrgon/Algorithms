package com.my.pro;

public class ReverseInteger {

    public static void main(String[] args) {

        int reverse1 = reverse(123);
        int reverse2 = reverse(-123);
        int reverse3 = reverse(120);
        System.out.println();
    }

    public static int reverse(int x) {

        final int MAX_32_INTEGER = Integer.MAX_VALUE / 10;
        final int MIN_32_INTEGER = Integer.MIN_VALUE / 10;

        int result = 0;

        while (x != 0) {
            int digit = x % 10;

            if (result > MAX_32_INTEGER || (result == MAX_32_INTEGER && digit > 7)) return 0;
            if (result < MIN_32_INTEGER || (result == MIN_32_INTEGER && digit < -8)) return 0;

            result = result * 10 + digit;
            x = x / 10;
        }
        return result;
    }
}