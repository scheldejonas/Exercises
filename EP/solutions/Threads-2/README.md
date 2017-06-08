# Threads-2

Task description [here](https://github.com/scheldejonas/Exercises/blob/master/EP/Exam-preparation-threads-2.pdf)

## General part

- When and why will we use Threads in our programs?
  - When you have something that is ressource intensive, you want to separate it over more threads aka. cores, to make the product responsive in a GUI i.e.
- What is the Race Condition Problem and how can you solve it?
  - Race Condition is problems on variables or reference spaces in ram being accesed from 2 or more different threads, at the same time. This makes the result not being concatinated with both values i.e.
  - The ways to solve Race condition is by placing our threads on the highest level of java code control to run a whole section of code, before next request is able to use those specified instructions in the given methods and variables bound to that section. Use Synchronized methods, Reentrant Locks or Atomic Variables to solve it in code sections.
- Explain the Producer/Consumer-problem and how to solve it in modern Java Programs
  - We have setup the producer to be controlled on when to put items into the queue, through the Implementation ArrayBlockingQueue. It automaticly holds, if it is not able to put data into the queue, because something else is using it at the moment, or the queue is full.
  - We have then setup the consumer to be controlled by a empty check inside a while true loop. This is then just take the next number in line from the queue, if there is one available. The take method also awaits if the queue is used from something else at the moment. Lastly to make sure the Consumer is not deadlocking the software, it is controlled from upper level in Application, on interrupt. which means all the producers are done working, and therefore interrupting the consumer afterwards here.
- Explain what Busy Waiting is and why it's a bad thing in a modern software system.
  - Busy Waiting is a loop that constantly runs and checks for some conditions to be true inside it, and then becomes done, for it to continue.
  - The reason it is bad, is because we all need quick resonsive interfaces. And it loses all it's reasons for coding the Busy Waiting loops, when the oberserver pattern is available to use with multithreading.
- Describe Java's BlockingQueue interface, relevant implementations and methods relevant for the producer consumer problem.
  - BlockingQueue is a interface, made specifically for multithreading. Where only one thread can (acces, read / write at a time). The methods put, take are both used when storing and retrieving using thread safe implementation. We used ArrayBlockingQueue for it's dynamic size allocation and ease of use through addAll, put, take and more. 

## Pratical part

Task artifacts:

1. ```java
   private long fib(long n) {
     if ((n == 0) || (n == 1)) {
       return n;
     } else {
       return fib(n - 1) + fib(n - 2);
     }
   }
   ```

   Hints:

   1. Initialize S1 with the following numbers: 4,5,8,12,21,22,34,35,36,37,42
   2. Use a BlockingQueue implementation (ArrayBlockingQueue) for both of the shared data structures (S1 and S2).
   3. For S1 you can use any of the possible insertion methods, but use poll() to take items out. If this method returns null you know that the list is empty (there are no more numbers to be calculated).
   4. For S2 you should use the put() and take() to insert / retrieve elements since they take care of the wait / wake.

Questions:

1. A thread (in this case the main-thread) must initially fill a shared data structure S1 with numbers from which the corresponding Fibonacci numbers should be calculated.
   1. Fibonacci (Creates BlockingQueue, and add's the 11 random numbers.)


2. The Main thread should start the four Producer threads (P1 - P4), that all uses the shared data structure S1 to retrieve values for which they should calculate the corresponding Fibonacci number. When a thread has finished the calculation, it should add the result to the shared data structure S2, and continue with the next number in S1. If S1 is empty, the producer should stop (not sleep or wait).
   1. CalculateThread (takes the static Fibonacci Queue and calculates fibonacci, from recursive method below, and then stores the result into a blocking queue inside Fibonacci)
   2. Fibonacci (Holds the recursive fibonacci calculation method and boths queues)
   3. Application (Creates the fibonacci, which constructs with the randoms numbers in S1 queue. After that creates and runs 4 threads of Calculate Thread.)
3. Just after the main thread have started the four Producer threads, it should start the Consumer-thread C1, which continuously retrieves the calculated figures, prints them to the console and keep track of the total sum.
   1. PrintQueueS2Thread (Runs constantly and awaits S2 in Fibonacci to receive numbers for printing. When there printing immediatly. It holds the total sum number)
4. When all threads have finished their jobs, print the sum of all the calculated Fibonacci numbers.
   1. PrintQueueS2Thread (After all calculate threads is finished, the application thread continuous. Then it interrupts the Print Thread, and goes through a last isInterrupted check to print total sum. And Breaks the while true loop)
5. Refactor all code in to a method which takes the number of Producer threads to use as an argument. Execute the method with 1, 2, 3, and 4 as argument. Calculate the time it takes for each execution and explain (as good as you can) the result.
   1. Application (calculateCrazyFibo method runs the whole calculation and takes the count of threads, that must be started to help solving fibonacci number of S1)