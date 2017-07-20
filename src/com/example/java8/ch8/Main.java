package com.example.java8.ch8;

import com.exmpale.java8.model.CaloricLevel;
import com.exmpale.java8.model.Dish;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Main {

    final static Map<String, Supplier<Product>> map = new HashMap<>(); static {
        map.put("loan", Loan::new); map.put("stock", Stock::new); map.put("bond", Bond::new);
    }


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

        // Strategy Pattern
        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean b2 = lowerCaseValidator.validate("bbbb");

        // Using lambda expressions

        Validator numericValidator2 = new Validator(s -> s.matches("\\d+"));
        boolean b3 = numericValidator2.validate("aaaa");

        Validator lowerCaseValidator2 = new Validator(s -> s.matches("[a-z]+"));
        boolean b4 = lowerCaseValidator2.validate("bbbb");

        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");

        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("money")){
                System.out.println("Breaking news in NY! " + tweet); }
        });
        f.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("queen")){
                System.out.println("Yet another news in London... " + tweet); }
        });

        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellcheckerProcessing();

        p1.setSuccessor(p2);
        String result = p1.handle("Aren`t labdas really sexy?!!" );
        System.out.println(result);

        UnaryOperator<String> headerProcess = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellChckerProcessing = (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcess.andThen(spellChckerProcessing);
        pipeline.apply("Aren`t labdas really sexy?!!");

        Product p = ProductFactory.createProduct("loan");
        
    }

    public static void doSomething(Runnable r) {
        r.run();
    }

    public static void doSomething(Task a) {
        a.execute();
    }

    public static Product createProduct(String name){
        Supplier<Product> p = map.get(name);
        if(p != null) return p.get();
        throw new IllegalArgumentException("No such product " + name);
    }
}
