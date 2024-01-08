package com.my.pro;

public class CountWaysToN {

    public static void main(String[] args) {
        int i = countWaysToN(3);
        System.out.println();
    }

    /*
     *   dp[0] = 1, dp[1] = 1, dp[2] = 2
     *   dp[i] = dp[i-1] + dp[i-2]+ dp[i-3]
     *
     *  1) 1+1+1
     *  2) 1+2
     *  3) 2+1
     *  4) 3
     */

    public static int countWaysToN(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }
}