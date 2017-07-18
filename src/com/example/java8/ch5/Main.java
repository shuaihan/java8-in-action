package com.example.java8.ch5;

import com.exmpale.java8.model.Dish;
import com.exmpale.java8.model.Trader;
import com.exmpale.java8.model.Transaction;
import javafx.scene.shape.Path;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        // Finding an element
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
        dish.ifPresent( dish1 -> System.out.println(dish1.getName()));
 
                numbers1.stream().map(x -> x*x).filter( x -> x%3 ==0)
                        .findFirst().ifPresent(System.out::println);

        int sum = numbers.stream().reduce(0, (i, j) -> i + j);
        sum = numbers.stream().reduce(0, Integer::sum) ;
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        count = menu.stream().count();

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950)
        );

        // 1. Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> tr2011 = transactions.stream()
                .filter( transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        // 2. What are all the unique cities where the traders work?
        // method1
        transactions.stream().map(transaction -> transaction.getTrader().getCity())
                .distinct() //
                .collect(Collectors.toList());
        // method2
        Set<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity()) .collect(Collectors.toSet());

        // 3. Find all traders from Cambridge and sort them by name
        List<Trader> traders = transactions.stream()
                .map(c -> c.getTrader())
                .filter(trader -> "Cambrige".equals(trader.getName()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        // 4.Return a string of all traders’ names sorted alphabetically
        // method1
        String traderNames =  transactions.stream()
                .map(c -> c.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (a, b) -> a + ", " + b);
        System.out.println(traderNames);

        // method2

        traderNames = transactions.stream().map(c -> c.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
        System.out.println(traderNames);

        // 5. Are any traders based in Milan?
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

       // 6. Print all transactions’ values from the traders living in Cambridge 
        transactions.stream().filter(c -> c.getTrader().getCity().equals("Cambridge"))
                .map(c -> c.getValue())
                .forEach(System.out::println);

        // 7. What’s the highest value of all the transactions?
        Optional<Integer> highestValue = transactions.stream().map(c -> c.getValue())
                .reduce(Integer::max);

        // Find the transaction with the smallest value
        // method1
        Optional<Transaction> smallestTransaction =
                transactions.stream().reduce((a, b) -> a.getValue() < b.getValue() ? a : b);

        // method2
        Optional<Transaction> smallestTransaction2
                = transactions.stream().min(Comparator.comparing(Transaction::getValue));


        // mapping to a numeric stream
        int calories = menu.stream().mapToInt(Dish::getCalories).sum();

        // Converting back to a stream of objects
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        //  Numeric ranges
        IntStream evenNumber = IntStream.rangeClosed(1, 100).filter(n -> n%2 ==0);
        System.out.println(evenNumber.count());

        Stream<String> stream1 = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        Stream<String> emptyStream = Stream.empty();

        int[] numbersx = { 2,3,5,7,11,13} ;
        sum = Arrays.stream(numbersx).sum();

        // Streams from files

        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("README.md"), Charset.defaultCharset())) {
              uniqueWords = lines.flatMap( line -> Arrays.stream(line.split(" ")))
                      .distinct()
                      .count();

        }
        catch(IOException e) {

        }

        // Streams from functions: creating infinite streams!

        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        // Generate
        Stream.generate(Math::random)
                .limit(5) .forEach(System.out::println);



        fibonacciTuplesSeriesByIterate();
        fibonacciTuplesSeriesByGenerate();
    }


    /*
    * input 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55
    * output  (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21).
    * */
    public static void fibonacciTuplesSeriesByIterate() {
        Stream.iterate(new int[] {0, 1},
                t -> new int[]{t[1], t[0] + t[1]}
                ).limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

        Stream.iterate(new int[] {0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .mapToInt(c -> c[0])
                .forEach(System.out::println);
    }

    public static void fibonacciTuplesSeriesByGenerate() {

    }

    
}
