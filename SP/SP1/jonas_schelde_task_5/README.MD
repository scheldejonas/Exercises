## SP1 - Task 5 with Jonas Schelde

#### Thread Programming and Non-blocking Java Servers

General part

Explain about Thread Programming including:

- TASK: *When and why we will use Threads in our programs?*
- ANSWER: We are using threads in a program when we wanted to seperate
  requests made from the Users on to more Processors.
- TASK: *Explain about the Race Condition Problem and ways to solve it in Java.*
- ANSWER: Race Condition is problems on variables or refenrece spaces in ram
  being accesed from 2 or more different threads, in a value destroying order.
- ANSWER: The ways to solve Race condition is by placing our threads on the highest level of java code control
  to run a whole section of code, before next request is able to use those specified instructions 
  in the given methods and variables bound to that section. Use Synchronized methods, Reentrant Locks and 
  Atomic Variables to solve it in code sections.
- TASK: *Explain how we can write reusable non-blocking Java Controls using Threads*
- ANSWER: use a Service Executor, which has an CachedThreadPool implementation, to solve these issues automaticly. 
- TASK: *Explain about deadlocks, how to detect them and ways to solve the Deadlock Problem*
- ANSWER: Detecting deadlocks, is when code is written with lock.lock() locks.
- ANSWER: Also deadlocks is when running a sleeping method or oberserving method that has lock on it.

#### Practical part

- TASK: *In this exercise we will simulate a large football stadium with many turnstiles that each updates a shared 
  counter, for each spectator that passes a turnstile.*
- TASK: *The turnstiles should use a Network Connection and TCP to update the shared counter.*
- TASK: *Implement a Java Based server that can handle n-turnstile clients without any lost updates.*
- ANSWER: See the TurnstileServer.java

#### Sub practical tasks

- TASK: *1. Design a TCP server and a simple protocol where each turnstile initially reports that it is a
  Turnstile (to distinguish from Monitor-Clients, see next step), its id (turnstile1-turnstile-n) and then reports an 
  increment for each spectator that passes the turnstile*
- ANSWER: Check the defineTheConnecterUnit() method in TurnstileServer.java
- TASK: *2. Identify potential Race Condition Problems and handle the problem(s).*
- ANSWER: 
- TASK: *3. For this exercise you don’t have to implement the turnstile-clients. Use Telnet to simulate the turnstiles.*
- TASK: *4. Extend the system, so A Monitor-Client can request the current total amount of spectators*
- TASK: *5. Change the example to make it possible to see the count from each turnstile*
- TASK: *6. If you have time-1: Deploy your server to DigitalOcean and demonstrate using this remote server*
- TASK: *7. If you have time-2: Write a simple client (Swing or Web-based) that can show the total amount of spectators*

## FOR INFORMAION ONLY

Note: this part is NOT a part of the exercise, it's meant as FYI and such a section will not be included with the real question.

Hints:

Don't do the deployment part, unless/before you have completed the exercise " Getting Started with Digital Ocean.pdf"

For part-1 (the protocol) it could be as simple as you have to send "things" in this order: TURNSTYLE (to signal that this client is a turnstile, this is sent only once)

T-1 (the id of this turnstyle, this is sent only once)

Count (the value to update the shared counter with, sent as many times a necessary) Feel free to come up with an alternative protocol.

Remember: For the final exam the grades for passed lies between 02 - 12.

You are not ALL expected to be able to complete all steps for an Exam exercise. For this exercise, try to complete at least step 1-3.