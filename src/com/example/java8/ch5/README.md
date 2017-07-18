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
 
