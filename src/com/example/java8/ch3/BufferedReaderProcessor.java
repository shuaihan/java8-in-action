package com.example.java8.ch3;

import java.io.BufferedReader;

/**
 * Created by shuaihan on 2017. 7. 17..
 */
@FunctionalInterface
public interface BufferedReaderProcessor {

    String process(BufferedReader br);
}
