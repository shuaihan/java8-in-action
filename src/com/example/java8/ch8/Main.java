package com.example.java8.ch8;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        // From anonymous classes to lambda expressions
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        Runnable r2 = () -> System.out.println("Hello");
        
    }
}
