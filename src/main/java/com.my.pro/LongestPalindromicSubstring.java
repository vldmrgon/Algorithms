package com.my.pro;

public class LongestPalindromicSubstring {
    static int start, end;

    public static void main(String[] args) {

        String s1 = "babad";
        String s2 = "cbbd";

        String palindrome1 = longestPalindrome(s1);
        String palindrome2 = longestPalindrome(s2);
        System.out.println();
    }

    public static String longestPalindrome(String s) {

        if (s == null || s.length() <= 1) return s;

        start = 0;
        end = 0;

        for (int i = 0; i < s.length(); i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }
        return s.substring(start, end + 1);
    }

    private static void expandAroundCenter(String str, int left, int right) {
        while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        if (right - left - 2 > end - start) {
            start = left + 1;
            end = right - 1;
        }
    }
}