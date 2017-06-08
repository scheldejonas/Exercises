package model;

/**
 * Created by schelde on 08/06/17.
 */
public class CalculateThread extends Thread {

    private int id;

    public CalculateThread(int id) {
        this.id = id;
    }

    public void run() {
        while (true) {
            try {
                Fibonacci.getBlockingQueueS2().put(Fibonacci.fib(Fibonacci.getAndDeleteIntFromQueue()));
            } catch (Exception e) {
                System.out.println("...thread " + this.id + " is stopping, because there was no items left to get.");
                this.stop();
                break;
            }
        }
    }
}
