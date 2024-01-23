package com.my.pro;

import java.util.HashSet;
import java.util.Set;

public class MissingInteger {


    public static void main(String[] args) {

        int[] a = {1, 3, 6, 4, 1, 2};
        int number = solution(a);
        System.out.println();
    }


    public static int solution(int[] arr) {

        Set<Integer> sets = new HashSet<>(arr.length);

        for (int number : arr) {
            sets.add(number);
        }

        int counter = 1;
        while (sets.contains(counter)) {
            counter++;
        }
        return counter;
    }
}