package com.example.java8.ch3;

import com.exmpale.java8.model.Apple;

import java.util.Comparator;

/**
 * Created by shuaihan on 2017. 7. 17..
 */
public class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
    }
}
