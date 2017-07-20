package com.example.java8.ch11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
public class Shop {

    private String name;
    public Shop() {


    }

    public Shop(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        // to be implemented
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    // Listing 11.4. Implementing the getPriceAsync method
    public Future<Double> getPriceAsyn(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            double price = calculatePrice(product);;
            futurePrice.complete(price);

        }).start();;

        return futurePrice;
    }

    private double calculatePrice(String product) {
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    // Listing 11.2. A method to simulate a 1-second delay
    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
