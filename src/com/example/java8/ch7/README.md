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

 
# Reference
- [Fork Join Framework - ForkJoinPool](http://blog.naver.com/PostView.nhn?blogId=2feelus&logNo=220732310413)