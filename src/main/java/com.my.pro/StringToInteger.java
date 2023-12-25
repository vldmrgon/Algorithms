package com.my.pro;

public class StringToInteger {

    public static void main(String[] args) {

        int i1 = myAtoi("42");
        int i2 = myAtoi("  -42");
        int i3 = myAtoi("4193 with words");
        int i4 = myAtoi("words and 987");
        int i5 = myAtoi("3.14159");
    }

    public static int myAtoi(String s) {

        boolean isNegative = false;
        int result = 0;

        String str = s.trim();
        if (str.isEmpty()) return 0;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (i == 0 && (ch < '0' || ch > '9') && ch != '-' && ch != '+') return 0;

            if ((ch == '-' || ch == '+') && i == 0) {
                isNegative = (ch == '-');
                continue;
            }

            if (!Character.isDigit(ch)) break;

            int number = ch - '0';

            if (result > (Integer.MAX_VALUE - number) / 10) return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            result = result * 10 + number;
        }
        return isNegative ? -result : result;
    }
}