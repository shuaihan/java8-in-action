package com.example.java8.ch7;

import java.nio.file.Paths;

/**
 * Created by shuaihan on 2017. 7. 19..
 */
public class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if(Character.isWhitespace(c))  {
            System.out.println(c + " isWhitespace = " + toString());
            return lastSpace ? this : new WordCounter(counter, true);

        }
        else {
            System.out.println(c + " " + toString());
            return lastSpace ? new WordCounter(counter+1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        System.out.println(  "combine = " + wordCounter.toString());
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "WordCounter{" +
                "counter=" + counter +
                ", lastSpace=" + lastSpace +
                '}';
    }
}
