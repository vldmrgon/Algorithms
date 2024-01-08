package com.my.pro;

import java.util.HashMap;
import java.util.Map;

public class OddOccurrencesInArray {

    public static void main(String[] args) {
        int[] arr = new int[]{9, 3, 9, 3, 9, 7, 9};
        int solution = solution(arr);
        System.out.println();
    }

    public static int solution(int[] a) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int number : a) {
            if (frequency.containsKey(number)) {
                frequency.put(number, frequency.get(number) + 1);
            } else {
                frequency.put(number, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() % 2 != 0) return entry.getKey();
        }
        return 0;
    }
}