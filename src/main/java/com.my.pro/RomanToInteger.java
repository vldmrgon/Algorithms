package com.my.pro;

import java.util.Map;

public class RomanToInteger {
    private static final Map<Character, Integer> cache = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    public static void main(String[] args) {
        String s = "MCMXCIV";
        int i = romanToInt(s);
    }

    public static int romanToInt(String s) {
        int total = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int value = cache.get(s.charAt(i));

            if (value < prevValue) {
                total -= value;
            } else {
                total += value;
            }

            prevValue = value;
        }
        return total;
    }
}