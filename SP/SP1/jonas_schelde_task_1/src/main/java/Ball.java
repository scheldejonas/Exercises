/**
 * Created by scheldejonas on 03/02/17.
 */
public class Ball {

    /**
     * Execute the main method in ex3.BallDemo.java. There is a problem here. Can you find it?
     *
     * Solve the problem by rewriting the Ball class to extend ``Thread`,
     * so that 1) the balls can be started / stopped asynchronously and 2) the program
     * can be terminated by pressing the cross.
     *
     * Exercise 4 (race condition)
     * Use the code in the package ex4 as start code for this exercise. This program simulates a
     * large football stadium with many turnstiles that each updates a shared counter, for each
     * spectator that passes a turnstile.
     *
     * Initially, the code is set up to simulate 40 turnstiles, each (running in a separate thread)
     * simulates that 1000 spectators pases.
     *
     * Execute the code and observe the reslult of the shared counter.
     *
     * If the result is not as expected, solve the problem:
     * 1) Using the traditional synchronization
     * 2) Using a AtomicInteger
     * 3) Using a java.util.concurrent.locks.ReentrantLock
     */
}
