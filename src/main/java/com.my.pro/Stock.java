package com.my.pro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Stock {

    public static void main(String[] args) {

        int[] arr1 = {5, 15, 1, 13, 5};
        int[] result1 = getMaxProfit(arr1); // {12,2,3}

        int[] arr2 = {3, 6, 10, 2, 8};
        int[] result2 = getMaxProfit(arr2); // {7,3,10}
        System.out.println();
    }


    /*                                       15
     *                      10                      13
     *                            8
     *                   6
     *                                     5            5
     *                3
     *                         2                 1
     *   timeframe    0  1  2  3  4        0  1  2  3  4
     *
     *   1)           buyDay=

     *
     *
     *
     *
     * */

    public static int[] getMaxProfit(int[] prices) {

        int buyDay = 0, indexBuy = 0;
        int sellDay = 1, indexSell = 0;

        int maxProfit = prices[sellDay] - prices[buyDay];

        for (int i = 2; i < prices.length; i++) {
            if (prices[i] < prices[buyDay]) {
                buyDay = i;
            } else {
                int currentProfit = prices[i] - prices[buyDay];

                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                    sellDay = i;
                    indexBuy = buyDay;
                    indexSell = sellDay;
                }
            }
        }
        return new int[]{maxProfit, indexBuy, indexSell};
    }
}