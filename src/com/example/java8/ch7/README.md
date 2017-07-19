# Parallel data processing 
## Before Java 7
1. First, you needed to explicitly split the data structure containing your data into subparts. 
2. Second, you needed to assign each of these subparts to a different thread. 
3. Third, you needed to synchronize them opportunely to avoid unwanted race conditions, wait for the completion of all threads, and finally combine the partial results. 

Java 7 introduced a framework called fork/join to perform these operations more consistently and in a less error-prone way.



## Java 8
- A parallel stream is a stream that splits its elements into multiple chunks, processing each chunk with a different thread. Thus, you can automatically partition the workload of a given operation on all the cores of your multicore processor and keep all of them equally busy.

# Using parallel streams effectively
- If in doubt, measure
- Watch out for boxing. Automatic boxing and unboxing operations can dramatically hurt performance.
- operations such as limit and findFirst that rely on the order of the elements are expensive in a parallel stream. For example, findAny will perform better than findFirst because it isn’t constrained to operate in the encounter order. You can always turn an ordered stream into an unordered stream by invoking the method unordered on it
- For a small amount of data, choosing a parallel stream is almost never a winning decision.
```
if (task is small enough or no longer divisible) { 
	compute task sequentially
} else {
	split task in two subtasks
	call this method recursively possibly further splitting each subtask wait for the completion of all subtasks
	combine the results of each subtask
}


```

- Take into account how well the data structure underlying the stream decomposes. 
For instance, an ArrayList can be split much more efficiently than a LinkedList,

|source| decomposability|
|---|---|
|ArrayList| Excellent|
|LinkedList|Poor|
|IntStram.range| Excellent|
|Stream.iterate|poor|
|HashSet|Good|
|TreeSet|Good}

- Consider whether a terminal operation has a cheap or expensive merge step (for example, the combiner method in a Collector)


# Fork Join Framework 
well-known divide-and-conquer algorithm


# Spliterator

```
// T is the type of the elements traversed by the Spliterator.
public interface Spliterator<T> {
    
    /**
    * The tryAdvance method behaves in a way similar to a normal Iterator in the sense 
    * that it’s used to sequentially consume the elements of the Spliterator one by one, 
    * returning true if there are still other elements to be traversed.
    */
    boolean tryAdvance(Consumer<? super T> action); 
    
    /**
    * the trySplit method is more specific to the Spliterator interface because it’s used to partition off some of 
    * its elements to a second Spliterator (the one returned by the method),
    * allowing the two to be processed in parallel.
    */
    Spliterator<T> trySplit();
    
    /**
    * A Spliterator may also provide an estimation of the number of the elements
    * remaining to be traversed via its estimateSize method,
    */
    long estimateSize();
    
    // 
    int characteristics();
}

```

# Summary
- Internal iteration allows you to process a stream in parallel without the need to explicitly use and coordinate different threads in your code. 
- Even if processing a stream in parallel is so easy, there’s no guarantee that doing so will make your programs run faster under all circumstances. Behavior and performance of parallel software can sometimes be counterintuitive, and for this reason it’s always necessary to measure them and be sure that you’re not actually slowing your programs down.
- Parallel execution of an operation on a set of data, as done by a parallel stream, can provide a performance boost, especially when the number of elements to be processed is huge or the processing of each single element is particularly time consuming.
- From a performance point of view, using the right data structure, for instance, employing primitive streams instead of nonspecialized ones whenever possible, is almost always more important than trying to parallelize some operations.
- The fork/join framework lets you recursively split a parallelizable task into smaller tasks, execute them on different threads, and then combine the results of each subtask in order to produce the overall result.
- Spliterators define how a parallel stream can split the data it traverses.
 
 
# Reference
- [Fork Join Framework - ForkJoinPool](http://blog.naver.com/PostView.nhn?blogId=2feelus&logNo=220732310413)