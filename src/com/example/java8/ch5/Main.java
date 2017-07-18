package com.example.java8.ch5;

import com.exmpale.java8.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        long count = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();
        System.out.println("count : " + count);

        List<Dish> vegegtarianDishes = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

        // Filtering unique elements
        Stream.of(1,2,1,3,3,2,4)
                .filter( integer -> integer%2 == 0)
                .distinct()
                .forEach(System.out::println);

        // Truncating a stream
        List<Dish> dishes = menu.stream().filter( dish -> dish.getCalories() > 300)
                .limit(3).collect(Collectors.toList());

        // Skipping elements in a stream
        dishes = menu.stream().filter(dish -> dish.getCalories() > 300)
                .skip(2).collect(Collectors.toList()) ;
        System.out.println(dishes);
    }

    
}
