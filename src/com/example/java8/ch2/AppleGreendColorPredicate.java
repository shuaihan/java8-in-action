package com.example.java8.ch2;

import com.exmpale.java8.model.Apple;

/**
 * Created by shuaihan on 2017. 7. 17..
 *
 */
public class AppleGreendColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
