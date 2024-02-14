package com.my.pro;

public class GreedyAlgorithms {

// TODO: 2/14/24 Kruskal's algorithm for finding the minimum spanning tree of a graph.
// TODO: 2/14/24 Prim's algorithm for finding the minimum spanning tree.
// TODO: 2/14/24 Dijkstra's algorithm for finding the shortest paths from one vertex to
//       all others in a graph with non-negative edge weights.
// TODO: 2/14/24 Huffman's algorithm for data compression.

    public static void main(String[] args) {
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;

        double knapsackMaxValue = getKnapsackMaxValue(weights, values, capacity);
    }

    public static double getKnapsackMaxValue(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }
}