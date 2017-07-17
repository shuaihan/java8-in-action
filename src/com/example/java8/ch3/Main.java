package com.example.java8.ch3;

import com.exmpale.java8.model.Apple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;


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
}
