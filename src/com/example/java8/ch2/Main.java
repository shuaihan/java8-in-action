package com.example.java8.ch2;

import com.exmpale.java8.model.Apple;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    List<Apple> inventory = Arrays.asList(new Apple("green", 180), new Apple("yellow", 20), new Apple("red", 70));

	    List<Apple> result = filterGreenApples(inventory);
	    System.out.println(result);

	    List<Apple> redApples = filterAppleByColor(inventory, "red");
        List<Apple> greenApples = filterAppleByColor(inventory, "green");
	    System.out.println(redApples);
        System.out.println(greenApples);

        greenApples = filterApples(inventory, "green", 0, true);
        List<Apple> heavyApples = filterApples(inventory, "", 150, false);
        System.out.println(greenApples);
        System.out.println(heavyApples);

        // Different strategies for selecting an Apple
        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(heavyApples);
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

    // Second attempt: parameterizing the color
    public static List<Apple> filterAppleByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    //  Third attempt: filtering with every attribute you can think of
    public static List<Apple> filterApples(List<Apple> inventory,
                                           String color,
                                           int weight, boolean flag) {
        //  it’s really ugly
        // What if the farmer asks you to filter with different attributes of an apple, for example, its size, its shape, its origin, and so on?
        // what if the farmer asks you for more complicated queries that combine attributes, such as green apples that are also heavy? You’d either have multiple duplicated filter methods or one giant, very complex method.
        
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if( (flag && color.equals(apple.getColor()) ||
                (!flag && apple.getWeight() > weight))) {
                result.add(apple);
            }
        }
        return result;
    }

    // Behavior parameterization
    // 2.2.1. Fourth attempt: filtering by abstract criteria
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            //  you to separate the logic of iterating the collection to filter and the behavior to apply on each element of that collection.
            if(predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    
}
