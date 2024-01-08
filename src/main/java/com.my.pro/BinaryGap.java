package com.my.pro;

public class BinaryGap {

    public static void main(String[] args) {
        int i = binaryGap(3456);
        System.out.println();
    }

    public static int binaryGap(int n) {

        String binaryInteger = Integer.toBinaryString(n);


        int currentGap = 0;
        int maxGap = 0;

        for (char ch : binaryInteger.toCharArray()) {
            if (ch == '0') {
                currentGap++;
            } else {
                maxGap = Math.max(currentGap, maxGap);
                currentGap = 0;
            }
        }
        return maxGap;
    }
}
