# Threads

Task description [here](https://github.com/scheldejonas/Exercises/blob/master/EP/Exam-preparation-threads.pdf)

## General part

 Explain about Thread Programming including: 

- When and why we will use Threads in our programs? 
  - When you have something that is ressource intensive, you want to separate it over more threads aka. cores, to make the product responsive in a GUI i.e.
- Explain about the Race Condition Problem and ways to solve it in Java?
  - Race Condition is problems on variables or reference spaces in ram being accesed from 2 or more different threads, at the same time. This makes the result not being concatinated with both values i.e.
  - The ways to solve Race condition is by placing our threads on the highest level of java code control to run a whole section of code, before next request is able to use those specified instructions in the given methods and variables bound to that section. Use Synchronized methods, Reentrant Locks or Atomic Variables to solve it in code sections.
- Explain how Threads can help us in making responsive User Interfaces 
  - To make waiting tasks work in the background, while a user is i.e. moving around in the gui menu, while waiting for the information being i.e. fetched.
- Explain how we can write reusable non-blocking Java Controls using Threads and the observer Pattern
  - By making the controls be coded in a run method and implement the runnable interface.
  - From that in our Application main class, have say a CachedThreadPool Object, to start and execute the incoming jobs to Control classes.
    - For example a TCP server in it's controller waiting for new incoming connections. When ever it receives, it passes the info on to a runnable class in the model layer, for execution asynchronously. And all of this is handled in a CachedTheadPool, to make it managed for preventing starvation, race conditions and deadlocks better.
  - The observerpatterns makes us able to have the asynchronus work outside of the scope of the GUI. Therefore i.e. a form which runs in the main synchronus thread, can still have all the update or setText i.e. functions being synchronus activated. The rest of the fetching for some ressources, can in the observable class happen outside of the form scope and therefore never destroy the user experience with a hold or a wait time.

## Practical part

Rewrite the code using Threads and the Observer Pattern as explained below

1. Change the declaration of RandomUserControl to Extend java.util.Observable
   1. **RandomUserControl** (writing extends Observable)
2. Change the return type of the method fetchRandomUser() into void and replace the return statement with (explain what happens): 
   1. setChanged(); 
      1. **RandomUserControl** (The methods makes it possible to check hasChanged on update methods)
   2. notifyObservers(user); 
      1. **RandomUserControl** (Actually starts and runs the update method with the given object as second parameter called args)
3. Change the RandomUserForm to implement the java.util.Observable interface. When the RandomUserControl call notifyObservers(..), the update(..) method is called on all Observers and the arg-argument will include a RandomUser. 
   1. **RandomUserForm** (implements Observer, and update methods casts args to RandomUser Object, and sets input fields with data)
4. Move all the *.setText(..) methods from btn1Clicked (..) into the update(..) method (Create the variable random, as a RandomUser and initialize it by casting arg into a RandomUser. 
   1. **RandomUserForm** (makes a new Thread of RandomUserControl, which implements Runnable, and starts it.)
   2. **RandomUserControl** (when run method starts, it fetches a new user and notifys all observervs with the new user as arg.)
5. Register the Form as an Observer on the randomUserControl and call fetchRandomUser() in the btn1Clicked (..) method. 
   1. **RandomUserForm** (has the new Thread, and takes the runnable = RandomUserControl)
   2. **RandomUserControl** (the run method, runs the fetchRandomUser method and has al the notify code after received User)
6. Verify that everything works but, with the same problem as when we started (what have we gained by using the Observer Pattern so far?) 
   1. **RandomUserForm** (The GUI stills holds to be touched, when waiting for the person)
7. Move the contents of fetchRandomUser(..) into a separate Thread, that has visibility to the RandomUserControl, so its notifyObservers(..) method when it has received the data. This method notifies that all registered listeners.
   1. **RandomUserFrom** (removing the fetchRandomUser method to the Control)
   2. **RandomUserControl** (adding the fetchRandomUser in the run method to have it as separate Thread)
8. Finally, make sure that the code running on the GUI thread in the Forms update(..) method is thread-safe.
   1. **RandomUserFrom** (Adding the Reentranlock, and try/finaly block. Doing all the setText methods, in a locked area.)