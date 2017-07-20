package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
public class NYTimes implements Observer {
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("money")) {
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}