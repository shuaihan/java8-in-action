package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
public class Guardian implements Observer {
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}