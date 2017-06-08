import model.CalculateThread;
import model.Fibonacci;
import model.PrintQueueS2Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by schelde on 08/06/17.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        int threads = 3;
        long startTime = System.currentTimeMillis();
        calculateCrazyFibo(threads);
        long stopTime = System.currentTimeMillis();
        System.out.println("...it took exactly " + (stopTime - startTime) + " ms, to calculate the crazy fibo");
    }

    private static void calculateCrazyFibo(int threads) throws InterruptedException {
        Fibonacci fibonacci = new Fibonacci();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ExecutorService executorService = Executors.newFixedThreadPool(threads, threadFactory);
        for (int i = 0; i < threads; i++) {
            executorService.execute(new CalculateThread(i));
        }
        Thread thread = new PrintQueueS2Thread();
        thread.start();
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(1);
        }
        thread.interrupt();
    }
}
