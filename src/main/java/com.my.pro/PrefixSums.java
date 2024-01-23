package com.my.pro;

public class PrefixSums {


    public static void main(String[] args) {

        int[] arr = {0, 1, 0, 1, 1};
        int pass = passingCars(arr);

        int count = countDiv(6, 11, 2);


    }

    public static int passingCars(int[] cars) {
        /*
         *    0-1, 0,0-1, 0,0-1  5
         */

        int eastCar = 0;
        int passingCar = 0;

        for (int direct : cars) {

            if (direct == 0) {
                eastCar++;
            } else {
                passingCar += eastCar;

            }
            if (passingCar > 1_000_000_000) return -1;
        }
        return passingCar;
    }

    public static int countDiv(int A, int B, int K) {
        return (B / K) - (A - 1) / K;
    }
}