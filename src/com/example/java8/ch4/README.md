# Streams vs. collections
- Another view is that a stream is like a lazily constructed collection: 
values are computed when they’re solicited by a consumer.  a collection is eagerly constructed 
- a stream is a conceptually fixed data structure (you can’t add or remove elements from it) 
whose elements are computed on demand. 
- External vs. internal iteration : Using the Collection interface requires iteration to be done 
by the user (for example, using for-each); this is called external iteration. 
The Streams library by contrast uses internal iteration

# Intermediate vs. terminal operations
- What’s important is that intermediate operations don’t perform any processing until a terminal operation is invoked on the stream pipeline—they’re lazy

# Intermediate & Terminal operationsoperations

|Operation | Type| Return type |Argument of the operation |Function descriptor |
|---|---|---|---|---|
|filter|Intermediate|Stream<T>|Predicate<T>|T -> boolean|
|map|Intermediate|Stream<R>|Function<T, R>|T -> R|
|limit|Intermediate|Stream<T>||T -> boolean|
|sorted|Intermediate|Stream<T>|Comparator<T>|(T, T) -> int|
|distinct|Intermediate|Stream<T>||T -> boolean|
|forEach|Terminal| | |Consumes each element from a stream and applies a lambda to each of them. The operation returns void.|
|count|Terminal| | |Returns the number of elements in a stream. The operation returns a long.|
|collect|Terminal| | |Reduces the stream to create a collection such as a List, a Map, or even an Integer.|
