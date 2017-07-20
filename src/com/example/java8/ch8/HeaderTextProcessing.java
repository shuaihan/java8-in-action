package com.example.java8.ch8;

/**
 * Created by shuaihan on 2017. 7. 20..
 */
public class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String input) {
        return "From Raoul, Mario and Alan : " + input ;
    }
}
