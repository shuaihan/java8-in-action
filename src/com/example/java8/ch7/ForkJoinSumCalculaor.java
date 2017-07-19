package com.example.java8.ch7;

import java.util.concurrent.RecursiveTask;

/**
 * Created by shuaihan on 2017. 7. 19..
 */
// extend RecusiveTask to create a task usable with fork/join framework
public class ForkJoinSumCalculaor extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;

    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculaor(long[] numbers) {
        this(numbers, 0, numbers.length)  ;

    }

    private ForkJoinSumCalculaor(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    // Override the abstract method of RecursiveTask
    @Override
    protected Long compute() {
        // The size of the portion of the array summed by this task
        int length = end - start;
        if(length <= THRESHOLD) {
            // If the size is less than or equal to the threshold, compute the result sequentially.
            return computeSequentially();
        }

        // create substask to sum the first half of the array
        ForkJoinSumCalculaor leftTask =
                new ForkJoinSumCalculaor(numbers, start, start + length/2);

        //  Asynchronously execute the newly createe substask using another thread of the ForkJoinPool
        leftTask.fork();

        ForkJoinSumCalculaor rightTask =
                new ForkJoinSumCalculaor(numbers, start +  length/2, end);
        // Execute this second subtask synchronously, potentially allowing further recurvice spilits
        Long rightResult = rightTask.compute();
        // Read the result of the first subtask or wait for it if it isn`t ready.
        Long leftResult = leftTask.join();

        return   leftResult +   rightResult ;


       
    }


    private long computeSequentially() {
        long sum = 0;
        for(int i = start ; i < end ; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}
