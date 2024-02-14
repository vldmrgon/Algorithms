package com.my.pro;

public class DynamicProgramming {

    public static void main(String[] args) {
        int[] minAbs = {1, 5, 2, -2};
        int abs = minAbsMax(minAbs);
    }

    // TODO: 1/30/24 Knapsack Problem
    // TODO: 1/30/24 Resource Allocation Problem

    public static int minAbsMax(int[] A) {
        int N = A.length;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            A[i] = Math.abs(A[i]);
            sum += A[i];
        }

        boolean[] dp = new boolean[sum + 1];
        
        dp[0] = true;
        int currentSum = 0;

        for (int k : A) {
            for (int j = currentSum; j >= 0; j--) {
                if (dp[j]) {
                    dp[j + k] = true;
                }
            }
            currentSum += k;
        }

        int answer = sum;
        for (int i = 0; i <= sum / 2; i++) {
            if (dp[i]) {
                answer = Math.min(answer, Math.abs(sum - 2 * i));
            }
        }
        return answer;
    }
}