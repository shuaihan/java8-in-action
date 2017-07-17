# Lambdas
- **Anonymous** — We say anonymous because it doesn’t have an explicit name like a method would normally have: less to write and think about!
- **Function** — We say function because a lambda isn’t associated with a particular class like a method is. But like a method, a lambda has a list of parameters, a body, a return type, and a possible list of exceptions that can be thrown.
- **Passed around** — A lambda expression can be passed as argument to a method or stored in a variable.
- **Concise** — You don’t need to write a lot of boilerplate like you do for anonymous classes.


# Common functional interfaces in Java 8
|Functional interface|Function descriptor|Primitive specializations|
|---|---|---|
|Predicate<T>|T -> boolean |IntPredicate, LongPredicate, DoublePredicate|
|Consumer<T> |T -> void    |IntConsumer, LongConsumer, DoubleConsumer|
|Function<T, R> |T -> R|IntFunction<R>, IntToDoubleFunction, IntToLongFunction, LongFunction<R>, LongToDoubleFunction, LongToIntFunction, DoubleFunction<R>, ToIntFunction<T>, ToDoubleFunction<T>, ToLongFunction<T>|
|Supplier<T> |() -> T |BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier |
|UnaryOperator<T> |T -> T |IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator  |
|BinaryOperator<T> |(T, T) -> T |IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator |
|BiPredicate<L, R> |(L, R) -> boolean | | 
|BiConsumer<T, U> |(T, U) -> void |ObjIntConsumer<T>, ObjLongConsumer<T>, ObjDoubleConsumer<T> |
|BiFunction<T, U, R> |(T, U) -> R |ToIntBiFunction<T, U>, ToLongBiFunction<T, U>, ToDoubleBiFunction<T, U> |
