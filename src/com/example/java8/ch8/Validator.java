package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 19..
 */
public class Validator {

    private final ValidationStrategy strategy;


    public Validator(ValidationStrategy strategy) {
        this.strategy = strategy;

    }

    public boolean validate(String s) {
        return this.strategy.execute(s);
    }
}
