package com.example.java8.ch3;

import com.exmpale.java8.model.Apple;

import java.util.Comparator;


public class Main {

    public static void main(String[] args) {
        // write your code here
        // Before
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        };

        // After (with lambda expressions):
        // 
        Comparator<Apple> byWeightWithLambda =  (a1, a2) -> {
            return a1.getWeight() - a2.getWeight() ;
        } ;

        
        // Functional interface
        Runnable r1 = () -> System.out.println("Hello world 1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hellow World 2");
            }
        } ;

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));
    }


    public static void process(Runnable r) {
        r.run();
    }
}
