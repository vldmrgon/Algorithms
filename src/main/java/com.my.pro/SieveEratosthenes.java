package com.my.pro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveEratosthenes {

    public static List<Integer> findPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        int n = 30;
        List<Integer> primes = findPrimes(n);
        System.out.println("Простые числа от 2 до " + n + ": " + primes);
    }
}