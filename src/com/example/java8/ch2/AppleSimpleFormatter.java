package com.example.java8.ch2;

import com.exmpale.java8.model.Apple;

/**
 * Created by shuaihan on 2017. 7. 17..
 */
public class AppleSimpleFormatter implements AppleFormatter{
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}