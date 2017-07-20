package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
public interface Subject {
    void registerObserver(Observer o);

    void notifyObservers(String tweet);
}
