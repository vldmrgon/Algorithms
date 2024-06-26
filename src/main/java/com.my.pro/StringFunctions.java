package com.my.pro;

import java.util.HashSet;
import java.util.stream.IntStream;

public class StringFunctions {

//    “,,hello,,world,” → [“”, “”, “hello”, “”, “world”, ""]
//
//            “a, b, c” and “a,b,c” and “   a,  b ,c” → [“a”, “b”, “c”]
//
//            “,,   he   llo,,world,” → [“”, “”,“he   llo”, “”, “world”, ""]
//            1
//
//            0 1 2 3 4 5 6 7 8
//            , , h e l l o , , world

    public static void main(String[] args) {

        String str = ",,    hel lo ,,world,";
        String[] newArray = parsing(str);

        String haystack = "hello wworld";
        String needle = "world";
        boolean b = strStr(haystack, needle);
    }


    /*
     *     h e l l o  w o r l d
     *
     *   1
     *   2
     *   3
     *   4
     *   5
     *   6
     *
     * */

    public static boolean strStr(String haystack, String needle) {
        HashSet<String> listWords = new HashSet<>();

        int cycle = haystack.length() - needle.length() + 1;
        int indexStart = 0;
        int indexEnd = needle.length();

        while (cycle > 0) {
            listWords.add(haystack.substring(indexStart, indexEnd));

            indexStart++;
            indexEnd++;
            cycle--;
        }
        return listWords.contains(needle);
    }

    public static String[] parsing(String msg) {

        String[] arr = new String[msg.length()];

        StringBuilder currentMessage = new StringBuilder();
        int j = 0;

        for (int i = 0; i < msg.length(); i++) {

            if (msg.charAt(i) == ',') {

                arr[j] = currentMessage.toString();
                currentMessage = new StringBuilder();
                j++;
            } else {
                currentMessage.append(msg.charAt(i));
            }
        }
        arr[j] = currentMessage.toString();
        return trim(arr);
    }

    public static String[] trim(String[] arr) {
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != null) {

                int left = 0;
                int right = arr[i].length() - 1;

                while (left <= right && Character.isWhitespace(arr[i].charAt(left))) {
                    left++;
                }

                while (left <= right && Character.isWhitespace(arr[i].charAt(right))) {
                    right--;
                }

                arr[i] = (left <= right) ? arr[i].substring(left, right + 1) : "";
            }
        }
        return arr;
    }
}