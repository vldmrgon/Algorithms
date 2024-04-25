package com.my.pro;

public class RecursiveAlgorithm {
    static int count = 0;

    public static void main(String[] args) {
        try {
            int pow = pow(2, (short) -3);

        } catch (StackOverflowError error) {
            System.out.println(count);
        }
    }

    public static int pow(double a, short n) {
        if (n == 1) return (int) a;
        System.out.println(a + " " + n);
        count++;
        return (int) (a * pow(a, (short) (n - 1)));
    }
}