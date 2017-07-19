package com.example.java8.ch7;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

    final static String SENTENCE =
            " Nel mezzo del cammin di nostra vita " +
                    "mi ritrovai in una selva oscura" + " ché la dritta via era smarrita ";


    public static void main(String[] args) {
        System.out.println(sequentialSum(3));
        System.out.println(parallelSum(3));

        System.out.println(sideEffectSum(100));
        System.out.println(sideEffectParallelSum(100));

        System.out.println(forkJoinSum(100000));

        System.out.println(countWordsIteratively(SENTENCE));
        System.out.println(countWordsIterativelyByFunctional(SENTENCE));
        System.out.println(countWordsParallel(SENTENCE));


    }

    public static long  sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1L)
                .limit(n)
                .parallel()   // flag set
                .reduce(0L, Long::sum);
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    // Now you know that shared mutable state doesn’t play
    // well with parallel streams and with parallel computations in general.
    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }


    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();

        ForkJoinTask<Long> task = new ForkJoinSumCalculaor(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()) {
            if(Character.isWhitespace(c)) {
                lastSpace = true;
            }
            else {
                if(lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    public static int countWordsIterativelyByFunctional(String s) {
        Stream<Character> stream = IntStream.range(0, s.length())
                .mapToObj(i -> s.charAt(i));

        WordCounter wordCounter = stream.parallel().reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();

    }

    public static int countWordsParallel(String s) {

        Spliterator<Character> spliterator = new WordCounterSpliterator(s);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        WordCounter wordCounter = stream.parallel().reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return wordCounter.getCounter();

    }

}
