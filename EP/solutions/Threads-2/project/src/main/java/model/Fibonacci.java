package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by schelde on 08/06/17.
 */
public class Fibonacci {

    private static BlockingQueue<Integer> blockingQueue = null;
    private static BlockingQueue<Long> blockingQueueS2 = null;

    public Fibonacci() {
        int[] intArray = {4,5,8,12,21,22,34,35,36,37,42};
        this.blockingQueue = new ArrayBlockingQueue<Integer>(intArray.length);
        for (int i : intArray) {
            this.blockingQueue.add(i);
        }
        this.blockingQueueS2 = new ArrayBlockingQueue<Long>(intArray.length);
    }

    public static long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    /**
     * Getting and deleting the int from existing queue.
     *
     * If errors happen it will return 0 or throw an exception, or InterruptedException inside it.
     * @return
     * @throws Exception
     */
    public static int getAndDeleteIntFromQueue() throws Exception {
        if (blockingQueue.isEmpty()) {
            throw new Exception("There was no items in the blockingQueue.");
        }
        try {
            return blockingQueue.take();
        } catch (InterruptedException e) {
            System.out.println("...while trying to take a thing from the queue. | getAndDeleteIntFromQueue() ");
            e.printStackTrace();
        }
        return 0;
    }

    public static BlockingQueue<Long> getBlockingQueueS2() {
        return blockingQueueS2;
    }
}