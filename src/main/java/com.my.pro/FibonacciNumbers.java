package com.my.pro;

public class FibonacciNumbers {

    public int[] solution(int[] A, int[] B) {
        int maxN = 0;

        for (int j : A) {
            maxN = Math.max(maxN, j);
        }

        int[] fib = new int[maxN + 2];
        fib[1] = 1;
        fib[2] = 2;

        for (int i = 3; i <= maxN + 1; i++) {
            fib[i] = (fib[i - 1] + fib[i - 2]) % (1 << 30);
        }

        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int n = A[i];
            int power = B[i];
            result[i] = fib[n + 1] % (1 << power);
        }
        return result;
    }
}