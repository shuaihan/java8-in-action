package com.example.java8.ch8;

import com.exmpale.java8.model.CaloricLevel;
import com.exmpale.java8.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    interface Task{
        public void execute();
    }

    public static void main(String[] args) {
	    // write your code here
        // From anonymous classes to lambda expressions
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        Runnable r2 = () -> System.out.println("Hello");

        doSomething(new Task() {
            public void execute() {
                System.out.println("Danger danger!!"); }
        });

        // But converting this anonymous class to a lambda expression results in an ambiguous method call, because both Runnable and Task are valid target types:

        // doSomething( () -> System.out.println("Danger danger!!"));

        // You can solve the ambiguity by providing an explicit cast (Task)
        doSomething( (Task)() -> System.out.println("Danger danger!!"));
        
        //  From lambda expressions to method references
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


        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menu.stream().collect(Collectors.groupingBy(Dish::getCaloricLevel));
        int totalCalories = menu.stream().map(Dish::getCalories)
                .reduce(0, (c1, c2) -> c1 + c2);
        totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        
    }

    public static void doSomething(Runnable r) {
        r.run();
    }

    public static void doSomething(Task a) {
        a.execute();
    }
}
