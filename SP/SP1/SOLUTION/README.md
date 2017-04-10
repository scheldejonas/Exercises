# SP 1 | Jonas Schelde

The overall description is [here](https://github.com/scheldejonas/Exercises/tree/master/SP/SP1)

## Exam task 1 | Threads and Non-blocking Reusable Controls

This task description is [here](https://github.com/scheldejonas/Exercises/blob/master/SP/SP1/Exam-preparation-threads.pdf)

### General part

- When and why we will use Threads in our programs?
  - We are using threads in a program when we wanted to seperate requests made from the Users on to more Processors.
- Explain about the Race Condition Problem and ways to solve it in Java
  - Race Condition is problems on variables or refenrece spaces in ram being accesed from 2 or more different threads, in a value destroying order.
  - The ways to solve Race condition is by placing our threads on the highest level of java code control to run a whole section of code, before next request is able to use those specified instructions in the given methods and variables bound to that section. Use Synchronized methods, Reentrant Locks and Atomic Variables to solve it in code sections.
- Explain how Threads can help us in making responsive User Interfaces
  - Threads makes it possible for let any type of repetative sub task og list of instructions, in our program, be done synchronously. It means the, when having some buttons that start processes, which takes more then a second to run, you are able to let the program handle this process in a seperate core proces, so another core processer can handle the continous pushing or typing in the user interface for example.
- Explain how we can write reusable non-blocking Java Controls using Threads and the observer Pattern
  - We are 

### Practical part

- Implement the Observer Patten, so when the big button is pressed you signal that the GUI would like to be notified when data is ready.
  - The solution is found [here]()
  - The Observer is implemented like this
- Move the time Consuming task into a separate Thread

