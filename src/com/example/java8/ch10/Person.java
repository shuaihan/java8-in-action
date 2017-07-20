package com.example.java8.ch10;

import java.util.Optional;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    /*
    public Optional<Car> getCarAsOptional() {
        return Optional.ofNullable(car);
    }
    */
}
