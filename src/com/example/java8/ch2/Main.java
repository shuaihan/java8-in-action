package com.example.java8.ch2;

import com.exmpale.java8.model.Apple;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    List<Apple> inventory = Arrays.asList(new Apple("green"), new Apple("yellow"), new Apple("red"));

	    List<Apple> result = filterGreenApples(inventory);
	    System.out.println(result);
    }


    // 2.1.1 First attempt : filtering green apples
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return result;
    }
}
