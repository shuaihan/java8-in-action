# Filtering unique elements
- filter(Predicate<? super T> predicate) 
- limit()
- skip()
- distinct() : according to the implementation of the hashCode and equals methods of the objects produced by the stream
    - equals  : equality
    - hashCode  : identity
- map(Function<? super T, ? extends R> mapper)
- flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
- anyMatch(Predicate<? super T> predicate)
- allMatch(Predicate<? super T> predicate)
- noneMatch(Predicate<? super T> predicate) : The opposite of allMatch is noneMatch
- findAny()
- findFirst
- reduce(T identity, BinaryOperator<T> accumulator);


# 
