package com.my.pro;

public class Palindrome {

    public static void main(String[] args) {
        boolean palindrome1 = isPalindrome(132231);
        boolean palindrome2 = isPalindrome(-121);
        boolean palindrome3 = isPalindrome(10);
        System.out.println();
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        int reversed = reverse(x, 0);
        return x == reversed;
    }

    private static int reverse(int x, int reversed) {
        if (x == 0) return reversed;
        int digit = x % 10;
        reversed = reversed * 10 + digit;
        return reverse(x / 10, reversed);
    }
}