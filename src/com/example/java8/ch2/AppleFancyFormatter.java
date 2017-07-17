package com.example.java8.ch2;

import com.exmpale.java8.model.Apple;

/**
 * Created by shuaihan on 2017. 7. 17..
 */
public class AppleFancyFormatter implements AppleFormatter{
    @Override
    public String accept(Apple apple) {
        String characteristic =  apple.getWeight() > 150 ? "heavy" : "light";
        return "A "  + characteristic +
                " " + apple.getColor() + " apple";
    }
}
