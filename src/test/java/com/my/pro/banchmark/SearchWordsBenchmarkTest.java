package com.my.pro.banchmark;

import org.openjdk.jmh.annotations.*;
import com.my.pro.DataGenerator;
import com.my.pro.SearchWords;

import java.util.concurrent.TimeUnit;
import java.util.List;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(iterations = 5, time = 1)
@Fork(value = 4, jvmArgs = {"-Xms256m", "-Xmx256m"})
public class SearchWordsBenchmarkTest {

    private final int NUMBER_OF_SENTENCES = 1000;
    private final int MAX_WORDS_PER_SENTENCE = 200;

    private final int NUMBER_OF_QUERIES = 50;
    private final int MAX_WORDS_PER_QUERY = 50;

    private final List<String> sentences = DataGenerator.generateSentences(NUMBER_OF_SENTENCES, MAX_WORDS_PER_SENTENCE);
    private final List<String> queries = DataGenerator.generateQueries(NUMBER_OF_QUERIES, MAX_WORDS_PER_QUERY);

    @Benchmark
    public void checkSearchWords() {
        SearchWords.Result.textQueries(sentences, queries);
    }

    @Benchmark
    public void checkSearchWords1() {
        SearchWords.Result.textQueries(sentences, queries);
    }

    @TearDown(Level.Trial)
    public void tearDown() {
        System.out.println("Max load factor: " + (NUMBER_OF_SENTENCES * MAX_WORDS_PER_SENTENCE * NUMBER_OF_QUERIES * MAX_WORDS_PER_QUERY));
    }
}