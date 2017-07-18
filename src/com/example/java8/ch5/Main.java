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

        //
        // How would you use streams to filter the first two meat dishes?
        dishes = menu.stream().filter(dish -> dish.getCalories() > 300).limit(2).collect(Collectors.toList());

        List<String> names = menu.stream().map(Dish::getName).collect(Collectors.toList());

        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action") ;
        words.stream().map(String::length).forEach(System.out::println);

        // Flattening streams
        // ["Hello", "World"] you’d like to return the list ["H", "e", "l", "o", "W", "r", "d"].
        words = Arrays.asList("Hello", "World") ;

        // Incorrect use of map to find unique characters from a list of words
        words.stream().map( word -> word.split("")).distinct().collect(Collectors.toList());

        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);

        // The current solution still doesn’t work! This is because you now end up with a list of streams (more precisely, Stream<Stream<String>>)
        words.stream().map(word -> word.split(""))
                .map(Arrays::stream).distinct().collect(Collectors.toList())  ;

        List<String> uniqueCharacters = words.stream().map(word -> word.split(""))
                .flatMap(Arrays::stream).distinct().collect(Collectors.toList());

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().map(n -> n*n).collect(Collectors.toList());

        // [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1
                .stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        //  Checking to see if a predicate matches at least one element
        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        // Checking to see if a predicate matches all elements
        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000)  ;
        
    }

    
}
