package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 19..
 */
public class IsNumeric implements ValidationStrategy {
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}