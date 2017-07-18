# Stream methods
- iterate, of, generate
- filter(Predicate<? super T> predicate) 
- limit()
- skip()
- distinct() : according to the implementation of the hashCode and equals methods of the objects produced by the stream
    - equals  : equality
    - hashCode  : identity
- map(Function<? super T, ? extends R> mapper)
- flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
![fiture](https://1.bp.blogspot.com/-RJseuNzmm7I/Vtb3pH7iPkI/AAAAAAAAE-s/ZJSxR4EnlSI/s640/Java%2B8%2BflatMap%2Bexample%2B.jpg)
- anyMatch(Predicate<? super T> predicate)
- allMatch(Predicate<? super T> predicate)
- noneMatch(Predicate<? super T> predicate) : The opposite of allMatch is noneMatch
- findAny()
- findFirst
- reduce(T identity, BinaryOperator<T> accumulator);
- count()

# Stream operations: stateless vs. stateful
- stateless operation vs stateful-bounded operation vs stateful-unbounded operation
reduce(), sum, max are  stateful-bounded operations. sorted(), distinct()  are  stateful-unbounded operations
 

# Summary
1. The Streams API lets you express complex data processing queries. Common stream operations are summarized in table 5.1
2. You can filter and slice a stream using the filter, distinct, skip, and limit methods.
3. You can extract or transform elements of a stream using the map and flatMap methods.
4. You can find elements in a stream using the findFirst and findAny methods. You can match a given
   predicate in a stream using the allMatch, noneMatch, and anyMatch methods.
5. These methods make use of short-circuiting: a computation stops as soon as a result is found; there’s
   no need to process the whole stream.
6. You can combine all elements of a stream iteratively to produce a result using the reduce method, for
   example, to calculate the sum or find the maximum of a stream.
7. Some operations such as filter and map are stateless; they don’t store any state. Some operations
   such as reduce store state to calculate a value. Some operations such as sorted and distinct also store state because they need to buffer all the elements of a stream before returning a new stream. Such operations are called stateful operations.
8. here are three primitive specializations of streams: IntStream, DoubleStream, and LongStream. Their operations are also specialized accordingly.
9. Streams can be created not only from a collection but also from values, arrays, files, and specific methods such as iterate and generate.
10. An infinite stream is a stream that has no fixed size.
            

# Reference
- [Stream API Document](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#method.summary)