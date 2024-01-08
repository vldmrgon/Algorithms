package com.my.pro;

public class ExponentialMovingAverage {

    public static void main(String[] args) {
        double[] data = {10, 12, 15, 18, 20};
        double alpha = 0.3;

        double[] emaResult = calculateEMA(data, alpha);

        System.out.print("EMA Result: ");
        for (double ema : emaResult) {
            System.out.print(ema + " ");
        }
    }

    /*
     *      EMAk =  α * xₖ + (1 - α) * EMAₖ₋₁
     *
     */

    public static double[] calculateEMA(double[] data, double alpha) {
        double[] emaValues = new double[data.length];
        emaValues[0] = data[0];

        for (int i = 1; i < data.length; i++) {
            emaValues[i] = roundToTwoDecimalPlaces(alpha * data[i] + (1 - alpha) * emaValues[i - 1]);
        }

        return emaValues;
    }

    private static double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}