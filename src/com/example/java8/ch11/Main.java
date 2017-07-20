package com.example.java8.ch11;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsyn("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        // Do some more tasks, like querying other shops
        doSomethingElse();
        // while the price of the product is being calculated
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");

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
