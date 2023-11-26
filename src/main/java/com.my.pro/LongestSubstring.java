package com.my.pro;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public static void main(String[] args) {
        String s1 = " ";
        String s2 = "abcabcbb";
        String s3 = "dvdf";

        int i1 = lengthOfLongestSubstring(s1);
        int i2 = lengthOfLongestSubstring(s2);
        int i3 = lengthOfLongestSubstring(s3);
        System.out.println();
    }
    /*
         0 1 2 3 4 5 6 7
         a b c a b c b b
       1 a b c -
       2   b c a -
       3     c a b -
       4       a b c -
       5         b c -
       6           c b -
    */

    public static int lengthOfLongestSubstring(String s) {

        if (s.isEmpty()) return 0;
        if (s.trim().isEmpty()) return 1;
        if (s.length() == 1) return 1;

        Set<Character> set = new HashSet<>(s.length());
        int maxLength = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            char current = s.charAt(end);

            while (set.contains(current)) {
                set.remove(s.charAt(start));
                start++;
            }

            set.add(current);
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}