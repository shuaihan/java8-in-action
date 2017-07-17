package com.example.java8.ch3;

/**
 * Created by shuaihan on 2017. 7. 17..
 */
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}
