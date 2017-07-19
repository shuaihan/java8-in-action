package com.example.java8.ch8;

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
        
        
    }

    public static void doSomething(Runnable r) {
        r.run();
    }

    public static void doSomething(Task a) {
        a.execute();
    }
}
