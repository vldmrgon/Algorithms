package com.my.pro;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;
import java.util.List;

public class DataGenerator {

    protected static final String[] WORDS = {
            "alice", "bob", "charlie", "david", "eve", "frank",
            "like", "ski", "text", "fall", "not", "love", "hate",
            "music", "art", "java", "coding", "program", "algorithm"
    };

    private static final Random random = new Random();

    public static List<String> generateSentences(int numberOfSentences, int maxWordsPerSentence) {
        return IntStream
                .range(0, numberOfSentences)
                .mapToObj(i -> generateSentence(maxWordsPerSentence))
                .collect(Collectors.toList());
    }

    public static List<String> generateQueries(int numberOfQueries, int maxWordsPerQuery) {
        return IntStream
                .range(0, numberOfQueries)
                .mapToObj(i -> generateQuery(maxWordsPerQuery))
                .collect(Collectors.toList());
    }

    private static int getRandomNumber(int maxNumber) {
        return random.nextInt(maxNumber);
    }

    private static String generateSentence(int maxWordsPerSentence) {
        int wordsCount = 1 + getRandomNumber(maxWordsPerSentence);
        return IntStream
                .range(0, wordsCount)
                .mapToObj(i -> WORDS[getRandomNumber(WORDS.length)])
                .collect(Collectors.joining(" "));
    }

    private static String generateQuery(int maxWordsPerQuery) {
        int wordsCount = 1 + getRandomNumber(maxWordsPerQuery);
        return IntStream
                .range(0, wordsCount)
                .mapToObj(i -> WORDS[getRandomNumber(WORDS.length)])
                .collect(Collectors.joining(" "));
    }
}