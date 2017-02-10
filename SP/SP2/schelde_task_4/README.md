##### Thread Programming

---

###### General part

1. When and why will we use Threads in our programs?

I will use, when i have a lot of loops inside one loop needs to be ran. (search algorithms)

When i need to recieve tcp or udp connections.

In basicly any place, where the thread (list of instructions) can run side by side (synchronus)
without need to interfere on variables.

2. What is the Race Condition Problem and how can you solve it?

Race conditions problems is when to threads, needs to acces the same variable, and
the scheduler stops in the middle of reading and writing to a variable, before another
synchron thread is accessing that same variable, and therefore is not getting the correct
value, because the first thread, didn't finish its addition or writing after first read.

3. Explain the Producer/Consumer-problem and how to solve it in modern Java Programs

The Producer/Consumer problem is the issue on having more "consumers" requesting a value
and the producer instructions is taking more time to produce the value fast enough for the
consumer, before the next consumers appears in the line of requst needs.

To solve it, is to use threaded methods, so that producers can be copied out on different
threads/cores to deliver to the consumers in line, simultanously or in the other term
synchronus.

4. Explain what Busy Waiting is and why it's a bad thing in a modern software system.

I think I can start to explain it this way as a metafore: Imagine you went to a hospital. 
and there were some people ahead of you. You can either keep checking if the Doctor is 
free and there is no one ahead of you. Or, you can read a book, and the assistant calls 
out your name when it is your turn to meet the Doctor. The first one is busy waiting, 
the second is sleeping. Sleeping has extra overhead, there has to be an assistant to call 
out names. (S)He has to maintain a list of patients ordered by when they came in. But 
busy waiting is stressful, the patients have to keep doing some work to find out if the 
Doctor is free.

The problem is in modern software, it takes to many ressources of waiting, for the system
to continue, when ready. better to solve it here is with observer pattern, when ever is
possible on receiving calls.

5. Describe Java's BlockingQueue interface, relevant implementations and methods
relevant for the producer consumer problem.

Java BlockingQueue. java.util.concurrent.BlockingQueue is a java Queue that support 
operations that wait for the queue to become non-empty when retrieving and removing an 
element, and wait for space to become available in the queue when adding an element.

Java BlockingQueue implementations are thread-safe. All queuing methods are atomic in 
nature and use internal locks or other forms of concurrency control.

Java BlockingQueue interface is part of java collections framework and it’s primarily 
used for implementing producer consumer problem. We don’t need to worry about waiting 
for the space to be available for producer or object to be available for consumer in 
BlockingQueue because it’s handled by implementation classes of BlockingQueue.

Java provides several BlockingQueue implementations like ArrayBlockingQueue, 
LinkedBlockingQueue, PriorityBlockingQueue, SynchronousQueue and more.

---

#####