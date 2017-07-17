package com.example.java8.ch3;

/**
 * Created by shuaihan on 2017. 7. 17..
 */
public class Letter {

    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
