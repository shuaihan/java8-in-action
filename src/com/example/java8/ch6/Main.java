package com.example.java8.ch6;

import com.exmpale.java8.model.CaloricLevel;
import com.exmpale.java8.model.Dish;

import java.util.*;
import java.util.stream.Collectors;

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

        // Function<? super T, ? extends K> classifier
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy( dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return  CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })) ;
        System.out.println(dishesByCaloricLevel);


        // Multilevel grouping
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                        )
                );
        System.out.println(dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = menu
                .stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting())) ;


        System.out.println(typesCount);
        
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

        //  to adapt the result returned by a collector to a different type, you could use the collector returned by the Collectors.collectingAndThen factory method,
        Map<Dish.Type, Dish> mostCaloricByType2 =
                menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get
                ) ));
        System.out.println(mostCaloricByType2);

        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream()
                        .collect(
                                Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        System.out.println(totalCaloriesByType);

        Map<Dish.Type, Set<CaloricLevel>> calroricLevelByType =
                menu.stream().collect(
                  Collectors.groupingBy(Dish::getType,
                  Collectors.mapping( (dish) -> {
                      if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                      else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                      else return CaloricLevel.FAT;
                      }
        , Collectors.toSet()
                  )
                ));
        System.out.println(calroricLevelByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.mapping(
                        dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                        else return CaloricLevel.FAT; },
                        Collectors.toCollection(HashSet::new) )));

        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(Collectors.partitioningBy(

            Dish::isVegetarian
        )) ;

    }
}
