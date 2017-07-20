package com.example.java8.ch8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
class Feed implements Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}