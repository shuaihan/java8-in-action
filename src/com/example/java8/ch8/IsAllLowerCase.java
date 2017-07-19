package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 19..
 */
public class IsAllLowerCase implements ValidationStrategy {
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}