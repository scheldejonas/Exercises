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
  - We are basicly using the observer Pattern to 

### Practical part

- Implement the Observer Patten, so when the big button is pressed you signal that the GUI would like to be notified when data is ready.

  - The solution is found [here](https://github.com/scheldejonas/Exercises/blob/master/SP/SP1/SOLUTION/jonas_exam_task_threads/src/threadsObserver/synchron_gui_actions/StartFecthingNewRandomUser_ToObservers.java)

  - The Observer is implemented like this

    ```java
    public class StartFecthingNewRandomUser_ToObservers extends Observable implements Runnable {
        private RandomUserControl randomUserControl = new RandomUserControl();

        public StartFecthingNewRandomUser_ToObservers() {
        }

        @Override
        public void run() {
            System.out.println("...synchron Random User Fetcher started.");
            RandomUser randomUser = randomUserControl.fetchRandomUser();
            setChanged();
            notifyObservers(randomUser);
            System.out.println("...synchron Random User Fetcher is finished, and has notified listeners.");
        }
    }

    private void startUpdateWithNewUserSeparatly() {
      StartFecthingNewRandomUser_ToObservers observableFetcher = new StartFecthingNewRandomUser_ToObservers();
      observableFetcher.addObserver(
        new Observer() {
          @Override
          public void update(Observable o, Object arg) {
            System.out.println("...GUI recieved Random User from fetcher.");
            RandomUser randomUser = (RandomUser) arg;
            setInputFieldsWithUser(randomUser);
            System.out.println("...GUI is done updating with recieved Random User.");
            addNewUserStatusLine.setText("Status: DONE, New random user fetched.");
          }
        }
      );
      new Thread(observableFetcher).start();
      addNewUserStatusLine.setText("Status: WAIT, Getting Random User.");
    }
    ```

- Move the time Consuming task into a separate Thread

  - It is implemented in task above.

