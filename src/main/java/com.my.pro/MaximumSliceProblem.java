package com.my.pro;

public class MaximumSliceProblem {

    public static void main(String[] args) {
        int[] prices = {23171, 21011, 21123, 21366, 21013, 21367};
        int[] kadanesArr = {-2, 1, -3, 4, 6, 7, 8, 9};
        int[] triplet = {3, 2, 6, -1, 4, 5, -1, 2};

        int trip = kadaneTripletSolution(triplet);
        int maxProfit = computeMaxProfit(prices);
        int kadaneSolution = kadaneSolution(prices);
    }

    private static int kadaneTripletSolution(int[] triplet) {
        return 0;
    }


    public static int kadaneSolution(int[] prices) {
        int maxProfit = 0;
        int currentProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int dailyProfit = prices[i] - prices[i - 1];
            currentProfit = Math.max(dailyProfit, currentProfit + dailyProfit);
            maxProfit = Math.max(currentProfit, maxProfit);

        }
        return maxProfit;
    }

    /*
     *         23171
     *                                        21367
     *                           21366
     *                     21123
     *                                  21013
     *               21011
     *           0     1     2     3      4     5
     */


    public static int computeMaxProfit(int[] prices) {
        int minPrice = prices[0], maxPrice = prices[1];
        int maxProfit = maxPrice - minPrice;

        if (maxProfit <= 0) minPrice = prices[1];

        for (int i = 2; i < prices.length; i++) {
            maxPrice = prices[i];
            if (maxPrice < minPrice) {
                minPrice = maxProfit;
            } else {
                maxProfit = Math.max(maxProfit, maxPrice - minPrice);
            }
        }
        return maxProfit;
    }
}