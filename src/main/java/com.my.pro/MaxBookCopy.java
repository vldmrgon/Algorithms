package com.my.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

public class MaxBookCopy {

    static Map<Integer, List<Integer>> valueUpdate = new HashMap<>();
    static Map<Integer, Integer> updateValue = new HashMap<>();

    private static List<Integer> copies = new ArrayList<>();
    private static Integer bookCount = 0;

    public static List<Integer> maximumBookCopies(List<Integer> portalUpdate) {


        for (int update : portalUpdate) {

            int positiveNumber = Math.abs(update);

            if (update > 0) {
                addBook(positiveNumber);
            } else {
                deleteBook(positiveNumber);
            }
            copies.add(23);
        }
        return copies;
    }

    private static void addBook(Integer update) {

        updateValue.compute(update, (key, value) -> value == null ? 1 : value + 1);

        valueUpdate.put(updateValue.get(update), List.of(update));
        System.out.println();
    }

    private static void deleteBook(Integer update) {
        updateValue.compute(update, (key, value) -> value == null ? 1 : value - 1);
        Integer value = updateValue.get(update);
        valueUpdate.remove(update);
//        valueUpdate.put(value, update);
        System.out.println();
    }

    private static void checkCounts(Integer update) {


    }

    public static void main(String[] args) throws IOException {

        List<Integer> result = MaxBookCopy.maximumBookCopies(List.of(6, 6, 2, -6, -2, -6));
        result.forEach(System.out::println);

        // TODO: 11/21/23 return (1,2,2,1,1,0)

    }
}
