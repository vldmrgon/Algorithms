package com.my.pro.banchmark;

public class JmhRunner {
    public static void main(String[] args) {
        JmhConfiguration.runMicrobenchmark(SearchWordsBenchmarkTest.class);
    }
}
