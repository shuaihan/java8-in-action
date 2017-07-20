package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
public class SpellcheckerProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return input.replaceAll("labda", "lambda");
    }
}
