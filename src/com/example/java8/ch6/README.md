# Collection, Collector, and collect!

## collect


- <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
- <R, A> R collect(Collector<? super T, A, R> collector);

## Collectors

```
/**
    T is the generic type of the items in the stream to be collected.
    A is the type of the accumulator, 
    the object on which the partial  result will be accumulated 
    during the collection process.
    R is the type of the object (typically, but not always, the collection) 
    resulting from the collect operation.
        
*/
public interface Collector<T, A, R> { 
    
    Supplier<A> supplier(); 
    BiConsumer<A, T> accumulator(); 
    Function<A, R> finisher(); 
    BinaryOperator<A> combiner(); 
    Set<Characteristics> characteristics();
}
```
![Logical steps of the sequential reduction process](http://apprize.info/javascript/action/action.files/image129.jpg)

|method signature|
|---|
|public static <T> Collector<T, ?, Long> counting()     |                                                                       
|public static Collector<CharSequence, ?, String> joining()    |                                                               
|public static Collector<CharSequence, ?, String> joining(CharSequence delimiter)  |                                          
|public static Collector<CharSequence, ?, String> joining(CharSequence delimiter, CharSequence prefix, Char sequence sufix) |
|public static<T,A,R,RR> Collector<T,A,RR> collectingAndThen(Collector<T,A,R> downstream, Function<R,RR> finisher) |
|public static <T> Collector<T, ?, Long> counting()|
|public static <T> Collector<T, ?, Optional<T>> maxBy(Comparator<? super T> comparator) |
|public static <T> Collector<T, ?, Integer> summingInt(ToIntFunction<? super T> mapper)|
|public static <T> Collector<T, ?, Double> summingDouble(ToDoubleFunction<? super T> mapper)                                                               |
|public static <T> Collector<T, ?, Double> averagingInt(ToIntFunction<? super T> mapper)                                                                  |
|public static <T> Collector<T, ?, Double> averagingLong(ToLongFunction<? super T> mapper)                                                               |
|public static <T> Collector<T, ?, Double> averagingDouble(ToDoubleFunction<? super T> mapper)                                                          |
|public static <T> Collector<T, ?, T> reducing(T identity, BinaryOperator<T> op)                                                                       |
|public static <T> Collector<T, ?, Optional<T>> reducing(BinaryOperator<T> op) 
|public static <T, U> Collector<T, ?, U> reducing(U identity, Function<? super T, ? extends U> mapper, BinaryOperator<U> op)                          |
|public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier)                                       |
|public static <T, K, A, D> Collector<T, ?, Map<K, D>> groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D>downstream)|
|public static <T, U, A, R>Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper, Collector<? super U, A, R> downstream) |
# Reference
- [yangbongsoo java8 gitbooks](https://yangbongsoo.gitbooks.io/study/content/part2-2_d568_c218_d615_b370_c774_d130_cc98_b9ac.html)