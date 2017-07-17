package com.example.java8.ch3;

import com.exmpale.java8.model.Apple;
import sun.tools.jconsole.Tab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


public class Main {

    public static void main(String[] args)  throws IOException{
        // write your code here
        // Before
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        };

        // After (with lambda expressions):
        // 
        Comparator<Apple> byWeightWithLambda =  (a1, a2) -> {
            return a1.getWeight() - a2.getWeight() ;
        } ;

        
        // Functional interface
        Runnable r1 = () -> System.out.println("Hello world 1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hellow World 2");
            }
        } ;

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));

        try {
            String line = processFile();
            System.out.println(line);

            // Step 4: Pass lambdas
            // String oneline = processFile( (br) -> br.readLine());

            // String twoLines = processFile( (br) -> br.readLine() + br.readLine());
        } catch(IOException ex) {

        }

        // Predicate
        List<String> stringList = Arrays.asList("a", "b" , "", "c", "");
        Predicate<String> noneEmptyString = (s) -> !s.isEmpty();
        List<String> result = fileter(stringList, noneEmptyString);
        System.out.println(result);

        forEach(Arrays.asList(1,2,3,4,5), (t) -> System.out.println(t));

        // Working with a Function
        List<Integer> list = map(Arrays.asList("lambda" ,"in", "action"), (s) -> s.length() );

    }


    public static void process(Runnable r) {
        r.run();
    }

    // ini / preparation code -> task A or B -> Clean up / finishing code

    public static String processFile() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("README.md"))) {
            // this is the line that does usefull work.
            return bufferedReader.readLine();
        }
    }

    // Step 1: Remember behavior parameterization
    // ex) String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    //  Step 2: Use a functional interface to pass behaviors
    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("README.md"))) {
            // this is the line that does usefull work.
            // Step 3: Execute a behavior!
            return p.process(bufferedReader);
        }
    }


    public static <T> List<T> fileter(List<T> list,  Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for(T t : list) {
            if(predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for(T t : list) {
            consumer.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}
