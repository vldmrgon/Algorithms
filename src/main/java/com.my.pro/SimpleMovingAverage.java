package com.my.pro;

public class SimpleMovingAverage {

    public static void main(String[] args) {

        double[] stockPrices = {25, 28, 30, 32, 35, 33, 29};

        double[] doubles = calculateSMA(stockPrices, 5);
    }

    /*
     *   (25+28+30+32+35)/5 = 150/5 = 30
     *   (28+30+32+35+33)/5 = 158/5 = 31.6
     *   (30+32+35+33+29)/5 = 159/5 = 31.8
     *
     *   X(t-2) + X(t-1) + X(t))/3 (timefrate)
     *
     *
     *
     *
     * */

    private static double[] calculateSMA(double[] prices, int timeframe) {

        int iteration = prices.length - timeframe + 1;
        double[] calculateValue = new double[iteration];

        for (int i = 0; i < iteration; i++) {

            double middle = 0.0;

            for (int j = i; j < timeframe + i; j++) {
                middle += prices[j];
            }
            middle /= timeframe;
            calculateValue[i] = middle;

        }
        return calculateValue;
    }
}