package com.example.java8.ch6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by shuaihan on 2017. 7. 19..
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T> > {

    // The supplier method has to return a Supplier of an empty result—a parameterless
    // function that when invoked
    // creates an instance of an empty accumulator used during the collection process.
    @Override
    public Supplier<List<T>> supplier() {
        return () -> new ArrayList<>(); // or () -> ArrayList::new
    }

    // Adding an element to a result container: the accumulator method
    // The accumulator method returns the function that performs the reduction operation.
    // When traversing the nth element in the stream,
    // this function is applied with two arguments, the accumulator being the result of the reduction
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return (list, item) -> list.add(item);  // or List::add
    }

    /**
     * Merging two result containers: the combiner method
     * The addition of this fourth method allows a parallel reduction of the stream
     * */
    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    /**
     * Applying the final transformation to the result container: the finisher method  '
     * The finisher method has to return a function that’s invoked at the end of the accumulation process, after having completely traversed the stream,
     * in order to transform the accumulator object into the final result of the whole collection operatio
     * */
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
