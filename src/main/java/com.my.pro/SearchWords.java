package com.my.pro;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.*;

import java.io.*;
import java.util.stream.Stream;

public class SearchWords {

    public static void main(String[] args) throws IOException {

        List<String> sentences = Arrays.asList(
                "bob and alice like to text each other",
                "bob does not like to ski but does not like to fall",
                "Alice likes to ski"
        );

        List<String> queries = Arrays.asList(
                "bob alice",
                "alice",
                "like"
        );
    }

    public static class Result {

        public static void textQueries(List<String> sentences, List<String> queries) {
            List<Map<String, Integer>> sentencesWordCount = sentences
                    .parallelStream()
                    .map(Result::countWords)
                    .collect(Collectors.toList());

            queries
                    .parallelStream()
                    .forEach(query -> processQuery(query, sentencesWordCount));
        }

        private static Map<String, Integer> countWords(String sentence) {
            return Stream
                    .of(sentence.split(" "))
                    .collect(Collectors.toMap(key -> key, value -> 1, Integer::sum));
        }

        private static void processQuery(String queries, List<Map<String, Integer>> sentencesWordCount) {
            String result = IntStream
                    .range(0, sentencesWordCount.size())
                    .filter(i -> matchesQuery(sentencesWordCount.get(i), queries.split(" ")))
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" "));

            System.out.println(result.isEmpty() ? "-1" : result);
        }

        private static boolean matchesQuery(Map<String, Integer> sentenceWords, String[] queryWords) {
            return Stream.of(queryWords).allMatch(sentenceWords::containsKey);
        }
    }
}