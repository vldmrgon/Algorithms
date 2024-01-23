package com.my.pro;

public class TheTownJudge {
    /*
    *           trustCount    trustedCount
    *    index   0 1 2 3 4       0 1 2 3 4
    *  init     [0,0,0,0,0]     [0,0,0,0,0]
    *
    *   1       [0,-1,0,0,0]    [0,0,0,1,0]  {1,3}
    *   2       [0,-2,0,0,0]    [0,0,0,1,1]  {1,4}
    *   3       [0,-2,-1,0,0]   [0,0,0,2,1]  {2,3}
    *   4       [0,-2,-2,0,0]   [0,0,0,2,2]  {2,4}
    *   5       [0,-2,-2,0,-1]  [0,0,0,3,2]  {4,3}
    *
    */

    public static int findJudge(int N, int[][] trust) {
        int[] trustCount = new int[N + 1];
        int[] trustedCount = new int[N + 1];

        for (int[] relation : trust) {
            int a = relation[0];
            int b = relation[1];
            trustCount[a]--;
            trustedCount[b]++;
        }

        for (int i = 1; i <= N; i++) {
            if (trustCount[i] == 0 && trustedCount[i] == N - 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] trustData = {
                {1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}
        };
        int judge = findJudge(4, trustData);
    }
}