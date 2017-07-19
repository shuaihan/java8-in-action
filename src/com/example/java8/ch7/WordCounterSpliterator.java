package com.example.java8.ch7;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by shuaihan on 2017. 7. 19..
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0 ;
    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    // Consume the current character
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        // return true if there are further characters to be consumed.
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        // return null to signal that the String to be parsed is small
        // enough to be processed sequentially.
        if(currentSize < 10) {
            return null;
        }

        // Set the candidate`s split position to be half of the String to be parsed
        for(int spiltPos = currentSize/2 + currentChar; spiltPos < string.length() ; spiltPos++) {
            if(Character.isWhitespace(string.charAt(spiltPos))) {
                Spliterator<Character> spliterator = new  WordCounterSpliterator(string.substring(currentChar, spiltPos));

                currentChar = spiltPos;
                return spliterator;
            }
        }

        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
