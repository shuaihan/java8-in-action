package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
public class LeMonde implements Observer {
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("wine")) {
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}