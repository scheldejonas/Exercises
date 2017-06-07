# Network

Here is the initial task description [here](https://github.com/scheldejonas/Exercises/blob/master/EP/Exam-preparation-network.pdf)

Also called (Thread Programming and Non-blocking Java Servers)

## General part

 Explain about Thread Programming including: 

- When and why we will use Threads in our programs? 
  - We are using threads in a program when we wanted to seperate requests made from the Users on to more Processors.
- Explain about the Race Condition Problem and ways to solve it in Java?
  - Race Condition is problems on variables or refenrece spaces in ram being accesed from 2 or more different threads, in a value destroying order.
  - The ways to solve Race condition is by placing our threads on the highest level of java code control to run a whole section of code, before next request is able to use those specified instructions in the given methods and variables bound to that section. Use Synchronized methods, Reentrant Locks and Atomic Variables to solve it in code sections.
- Explain how we can write reusable non-blocking Java Controls using Threads
  - By making the controls be coded in a run method and implement the runnable interface.
  - From that in our Application main class, have say a CachedThreadPool Object, to start and execute the incoming jobs to Control classes.
    - For example a TCP server in it's controller waiting for new incoming connections. When ever it receives, it passes the info on to a runnable class in the model layer, for execution synchronously. And all of this is handled in a CachedTheadPool, to make it managed for preventing starvation, race conditions and deadlocks better.
- Explain about deadlocks, how to detect them and ways to solve the Deadlock Problem 
  - For example:
    - Detecting deadlocks, is when code is written with lock.lock() locks.
    - Also deadlocks is when running a sleeping method or oberserving method that has lock on it.

## Practical part

- Design a TCP server and a simple protocol where each turnstile initially reports that it is a Turnstile (to distinguish from Monitor-Clients, see next step), its id (turnstile1-turnstile-n) and then reports an increment for each spectator that passes the turnstile
  - Server (start the server | separate new connections into ConnecionProcessThread)
  - ConnectionProcessThread (run's the socket for receiving new messages over TCP | in Thread)
  - StadiumProtocol (converts strings over network to commands and formats then for sending)
  - Database (holds the units map and the total people count)
- Identify potential Race Condition Problems that passes the turnstile
  - When 2 Turnstile want to add a person to the total count of people went through.
  - Solved by making AtomicLong, and addAndGet the number every time.
- For this exercise you don't have to implement the turnstile-clients. Use Telnet to simulate the turnstiles.
  - Telnet localhost 8080, made us able to write to the server in terminal.
- Extend the system, so A Monitor-Client can request the current total amount of spectators
  - Under Test code - MonitorClient (EPC | command) will get the total people amount.
- Change the example to make it possible to see the count from each turnstile.
  - Under Test code  - MonitorClient EAUWC | command) will echo all units's one by one with the people count.