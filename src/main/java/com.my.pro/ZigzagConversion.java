package com.my.pro;

public class ZigzagConversion {

    /*
     *             4 numbers (even)
     *      0 - o       o | 0,(6,0)
     *      1 - o    o  o | 1,(4,2)
     *      2 - o  o    o | 2,(2,4)
     *      3 - o       o | 3,(0,6)
     *
     *             5 numbers (odd)
     *      0 - o           o | 0,(8,0)
     *      1 - o        o  o | 1,(6,2)
     *      2 - o     o     o | 2,(4,4)
     *      3 - o  o        o | 3,(2,6)
     *      4 - o           o | 4,(0,8)
     *
     *
     *              PINALSIGYAHRPI
     * */

    public static void main(String[] args) {

        String Str1 = "PAYPALISHIRING";
        String Str2 = "PAYPALISHIRING";
        String Str3 = "A";

        String convert1 = convert(Str1, 4);
        String convert2 = convert(Str2, 3);
        String convert3 = convert(Str3, 1);
    }

    public static String convert(String s, int numRows) {
        if (numRows <= 1 || s.length() <= numRows) return s;
        StringBuilder result = new StringBuilder();

        int iterations = (numRows - 1) * 2;
        int length = s.length();

        for (int i = 0; i < numRows; i++) {
            int step1 = iterations - 2 * i;
            int step2 = 2 * i;

            int index = i;

            while (index < length) {
                if (step1 > 0) {
                    result.append(s.charAt(index));
                    index += step1;
                }

                if (step2 > 0 && index < length) {
                    result.append(s.charAt(index));
                    index += step2;
                }
            }
        }
        return result.toString();
    }
}