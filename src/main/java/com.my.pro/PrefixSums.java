package com.my.pro;

public class PrefixSums {

    public static void main(String[] args) {

        int[] avgArr = {4, 2, 2, 5, 1, 5, 8};
        int avg = minAvgTwoSlice(avgArr);

        String str = "CAGCCTA";
        int[] p = {2, 5, 0};
        int[] q = {4, 5, 6};
        int[] dna1 = dnaQueryAlgorithm(str, p, q);
        int[] dna2 = dnaQueryPrefixSolution(str, p, q);

        int count = countDiv(6, 11, 2);

        int[] arr = {0, 1, 0, 1, 1};
        int pass = passingCars(arr);
    }

    public static int minAvgTwoSlice(int[] arr) {
        int N = arr.length;
        if (N < 2) return 0;

        double minAvg = Double.MAX_VALUE;
        int minStartPos = 0;

        for (int start = 0; start < N - 1; start++) {
            double avg2 = (arr[start] + arr[start + 1]) / 2.0;
            double avg3 = (start < N - 2) ? (arr[start] + arr[start + 1] + arr[start + 2]) / 3.0 : Double.MAX_VALUE;

            if (avg2 < minAvg) {
                minAvg = avg2;
                minStartPos = start;
            }

            if (avg3 < minAvg) {
                minAvg = avg3;
                minStartPos = start;
            }
        }
        return minStartPos;
    }

    /*
     *          0 1 2 3 4 5 6         A - 1, C - 2, G - 3, T - 4   impact factor
     *          C A G C C T A
     *     1)       *---*
     *     2)             *
     *     3)   *-----------*
     *
     */
    public static int[] dnaQueryPrefixSolution(String S, int[] P, int[] Q) {
        int N = S.length();
        int M = P.length;
        int[] impactFactors = new int[N];

        for (int i = 0; i < N; i++) {
            char nucleotide = S.charAt(i);
            if (nucleotide == 'A') impactFactors[i] = 1;
            else if (nucleotide == 'C') impactFactors[i] = 2;
            else if (nucleotide == 'G') impactFactors[i] = 3;
            else if (nucleotide == 'T') impactFactors[i] = 4;
        }

        int[][] prefixSums = new int[4][N];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < N; j++) {
                int currentImpactFactor = impactFactors[j];
                int previousSum = (j > 0) ? prefixSums[i][j - 1] : 0;
                prefixSums[i][j] = previousSum + ((currentImpactFactor == i + 1) ? 1 : 0);
            }
        }

        int[] result = new int[M];
        for (int k = 0; k < M; k++) {
            int start = P[k];
            int end = Q[k];

            int[] nucleotideCounts = new int[4];
            for (int i = 0; i < 4; i++) {
                nucleotideCounts[i] = prefixSums[i][end] - ((start > 0) ? prefixSums[i][start - 1] : 0);
            }

            for (int i = 0; i < 4; i++) {
                if (nucleotideCounts[i] > 0) {
                    result[k] = i + 1;
                    break;
                }
            }
        }
        return result;
    }

    public static int[] dnaQueryAlgorithm(String str, int[] p, int[] q) {
        if (p.length != q.length) throw new ArithmeticException("Query not full");
        int[] arr = new int[p.length];

        for (int i = 0; i < p.length; i++) {
            int startIndex = p[i];
            int endIndex = q[i];

            int minImpactFactor = 0;

            for (int j = startIndex; j < endIndex + 1; j++) {
                char cr = str.charAt(j);
                int currentImpactFactor = getImpactFactor(cr);
                minImpactFactor = j == startIndex ? currentImpactFactor : Math.min(minImpactFactor, currentImpactFactor);
            }
            arr[i] = minImpactFactor;
            minImpactFactor = 0;
        }
        return arr;
    }

    private static int getImpactFactor(char ch) {
        if (ch == 'A') return 1;
        if (ch == 'C') return 2;
        if (ch == 'G') return 3;
        if (ch == 'T') return 4;
        return -1;
    }


    public static int countDiv(int A, int B, int K) {
        return (B / K) - (A - 1) / K;
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


}