package com.my.pro;

import java.util.HashMap;
import java.util.Map;

public class Leader {

    public static void main(String[] args) {
        int[] majorArray = {3, 4, 3, 2, 3, -1, 3, 3};
        int[] equiArray = {4, 3, 4, 4, 4, 2};
        int value = findMajorElement(majorArray);
        int equiLeader = findEquiLeader(equiArray);
    }

    /*
     *      0  1  2  3  4  5
     *      4  3  4  4  4  2
     *
     * 1   [4][3  4  4  4  2]
     * 2   [4  3][4  4  4  2]
     * 3   [4  3  4][4  4  2]
     * 4   [4  3  4  4][4  2]
     * 5   [4  3  4  4  4][2]
     *
     */
    //         Version, Leader
    static Map<Integer, Integer> leftCache = new HashMap<>();
    static Map<Integer, Integer> rightCache = new HashMap<>();

    public static int findEquiLeader(int[] equiArray) {
        for (int i = 0, j = equiArray.length - 1; i < equiArray.length; i++, j--) {
            addLeader(i, "Left", equiArray);
            addLeader(j, "Right", equiArray);
        }

        for (int z = 0; z < equiArray.length; z++) {
            int leftLeader = leftCache.get(z);
            int rightLeader = rightCache.get(equiArray.length - 1 - z);
            if (leftLeader == rightLeader) return leftLeader;
        }
        return 0;
    }

    public static void addLeader(int number, String cache, int[] equiArray) {

    }




    /*
     *
     *
     *          Map<number, count>
     *          majorCounter = 5;
     *          majorNumber = 4
     *
     *                                   0    1    2    3    4     5    6    7
     *                                   3    4    3    2    3    -1    3    3
     *  1 Check number in map and major 3,1
     *  2                                    4,1
     *  3                                         3,2
     *  4                                              2,1
     *  5                                                   3,3
     *  6                                                        -1,1
     *  7                                                              3,4
     *  8                                                                   3,5  return result = 3
     */

    private static int findMajorElement(int[] majorArray) {
        Map<Integer, Integer> cache = new HashMap<>();
        int majorNumber = 0;
        int majorCounter = 0;

        for (int number : majorArray) {

            if (!cache.containsKey(number)) {
                cache.put(number, 1);
                if (majorCounter == 0) {
                    majorCounter = 1;
                    majorNumber = number;
                }
            } else {
                Integer counter = cache.get(number);
                counter++;
                cache.put(number, counter);
                if (counter > majorCounter) {
                    majorCounter = counter;
                    majorNumber = number;
                }
            }
        }
        return majorNumber;
    }
}

