package com.example.java8.ch11;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

    }

    public static void longOperationAsynchronos() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(() -> {
             return doSomeLongComputation();
        })  ;

        doSomethingElse();

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch(ExecutionException ee) {

        } catch(InterruptedException ie) {

        } catch(TimeoutException te) {
            
        }
    }

    public static Double doSomeLongComputation() {
         return Double.valueOf(10.1);
    }

    public static void doSomethingElse() {

    }
}
