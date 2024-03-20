package com.my.pro;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.HashMap;
import java.util.*;

import java.io.*;

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

        // This is the basic implementation of the search algorithm
        // To work in big data, it's necessary to use parallel data processing and a reverse index algorithm

        private static Map<String, Integer> countWords(String sentence) {
            HashMap<String, Integer> wordCount = new HashMap<>();
            String[] words = sentence.split(" ");
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }


            return wordCount;
        }

        private static boolean matchesQuery(Map<String, Integer> sentenceWords, String[] queryWords) {
            for (String word : queryWords) {
                if (!sentenceWords.containsKey(word)) {
                    return false;
                }
            }
            return true;
        }

        public static void textQueries(List<String> sentences, List<String> queries) {
            List<Map<String, Integer>> sentencesWordCount = new ArrayList<>();

            for (String sentence : sentences) {
                sentencesWordCount.add(countWords(sentence));
            }

            for (String query : queries) {
                boolean found = false;
                String[] queryWords = query.split(" ");

                for (int i = 0; i < sentencesWordCount.size(); i++) {
                    if (matchesQuery(sentencesWordCount.get(i), queryWords)) {
                        found = true;
                        System.out.print(i + " ");
                    }
                }

                if (!found) {
                    System.out.print("-1");
                }
                System.out.println();
            }
        }

        public static void textQueries1(List<String> sentences, List<String> queries) {
            List<Map<String, Integer>> sentencesWordCount = sentences
                    .parallelStream()
                    .map(Result::countWords1)
                    .collect(Collectors.toList());

            queries
                    .parallelStream()
                    .forEach(query -> processQuery(query, sentencesWordCount));
        }

        private static Map<String, Integer> countWords1(String sentence) {
            return List
                    .of(sentence.split(" "))
                    .stream()
                    .collect(Collectors.toMap(key -> key, value -> 1, Integer::sum));
        }

        private static void processQuery(String queries, List<Map<String, Integer>> sentencesWordCount) {
            String result = IntStream
                    .range(0, sentencesWordCount.size())
                    .filter(i -> matchesQuery1(sentencesWordCount.get(i), queries.split(" ")))
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(" "));

            System.out.println(result.isEmpty() ? "-1" : result);
        }

        private static boolean matchesQuery1(Map<String, Integer> sentenceWords, String[] queryWords) {
            return List.of(queryWords).stream().allMatch(sentenceWords::containsKey);
        }

        private static Map<String, Set<Integer>> buildInvertedIndex(List<String> sentences) {
            return IntStream
                    .range(0, sentences.size())
                    .boxed()
                    .flatMap(i -> Arrays.stream(sentences.get(i).split(" "))
                            .map(word -> new AbstractMap.SimpleEntry<>(word, i)))
                    .collect(Collectors.groupingBy(
                            AbstractMap.SimpleEntry::getKey,
                            Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toSet())
                            )
                    );
        }

        private static void searchWithInvertedIndex(Map<String, Set<Integer>> invertedIndex, List<String> queries) {
            for (String query : queries) {
                String[] queryWords = query.split(" ");
                Set<Integer> matchingSentences = new HashSet<>();

                if (queryWords.length > 0 && invertedIndex.containsKey(queryWords[0])) {
                    matchingSentences.addAll(invertedIndex.get(queryWords[0]));
                }

                for (int i = 1; i < queryWords.length; i++) {
                    if (invertedIndex.containsKey(queryWords[i])) {
                        matchingSentences.retainAll(invertedIndex.get(queryWords[i]));
                    } else {
                        matchingSentences.clear();
                        break;
                    }
                }

                if (matchingSentences.isEmpty()) {
                    System.out.print("-1");
                } else {
                    for (Integer index : matchingSentences) {
                        System.out.print(index + " ");
                    }
                }
                System.out.println();
            }
        }

        public static void textQueriesWithReverseIndex(List<String> sentences, List<String> queries) {
            Map<String, Set<Integer>> invertedIndex = buildInvertedIndex(sentences);
            searchWithInvertedIndex(invertedIndex, queries);
        }
    }
}