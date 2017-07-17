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

# @FunctionalInterface
The compiler will return a meaningful error if you define an interface using the @FunctionalInterface annotation and it isn’t a functional interface. 

# Method reference
method reference 는 기존 메소드구현으로부터 람다 표현식을 생성해준다.

|type | example | recipes for construction method references |
|---|---|---|
|static method reference |  Integer: : parseInt | (args) -> ClassName.statisMethod(args) => ClassName::staticMethod |
|class method reference |String::length | (arg0, rest) -> arg0.instanceMethod(res) => ClassNAme::instanceMethod arg0 is of type Class Name |
|instance method reference | instanceObject::getValue() | (args) -> expr.instanceMethod(args) => expr::instanceMethod |


# Summary
- A lambda expression can be understood as a kind of anonymous function: it doesn’t have a name, but it has a list of parameters, a body, a return type, and also possibly a list of exceptions that can be thrown.
- Lambda expressions let you pass code concisely.
- A functional interface is an interface that declares exactly one abstract method.
- Lambda expressions can be used only where a functional interface is expected.
- Lambda expressions let you provide the implementation of the abstract method of a functional
  interface directly inline and treat the whole expression as an instance of a functional interface.
- Java 8 comes with a list of common functional interfaces in the java.util .function package, which includes Predicate<T>, Function<T, R>, Supplier<T>, Consumer<T>, and
  BinaryOperator<T>
- There are primitive specializations of common generic functional interfaces such as Predicate<T>
  and Function<T, R> that can be used to avoid boxing operations: IntPredicate,
  IntToLongFunction, and so on.
- The execute around pattern (that is, you need to execute a bit of behavior in the middle of code that’s
  always required in a method, for example, resource allocation and cleanup) can be used with lambdas
  to gain additional flexibility and reusability.
- The type expected for a lambda expression is called the target type.
- Method references let you reuse an existing method implementation and pass it around directly.
- Functional interfaces such as Comparator, Predicate, and Function have several default methods
  that can be used to combine lambda expressions.
  
     

