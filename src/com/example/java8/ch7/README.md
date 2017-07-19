# Parallel data processing 
## Before Java 7
1. First, you needed to explicitly split the data structure containing your data into subparts. 
2. Second, you needed to assign each of these subparts to a different thread. 
3. Third, you needed to synchronize them opportunely to avoid unwanted race conditions, wait for the completion of all threads, and finally combine the partial results. 

Java 7 introduced a framework called fork/join to perform these operations more consistently and in a less error-prone way.



## Java 8
- A parallel stream is a stream that splits its elements into multiple chunks, processing each chunk with a different thread. Thus, you can automatically partition the workload of a given operation on all the cores of your multicore processor and keep all of them equally busy.


# Reference
- 