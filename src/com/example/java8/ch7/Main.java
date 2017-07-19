package com.example.java8.ch7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Consumer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        System.out.println(sequentialSum(3));
        System.out.println(parallelSum(3));

        System.out.println(sideEffectSum(100));
        System.out.println(sideEffectParallelSum(100));

        System.out.println(forkJoinSum(100000));

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

}
