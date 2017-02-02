import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <h2>Task 2</h2>
 * <p>
 * This class should only return even numbers. It will be called from many
 * different threads, so it has to be thread-safe. In other words it has to be
 * able to avoid race-conditions if several threads calls it at once.
 * </p>
 * <p>
 * Your task is to implement the {@link #next()} method to return 0 as the
 * first number, 2 as the second, 4 and so on.
 * </p>
 */
public class Even implements Iterator<Long> {

    // The counter containing the number the iterator has reached.
    private Long counter =  new Long(-2);

    /**
     * This has 2^64 numbers. That's a lot!
     *
     * @return Always true.
     */
    @Override
    public boolean hasNext() {
        // Don't change this: we assume we always have another number

        return true;
    }

    /**
     * @return The next even number in the iterator.
     */
    @Override
    public Long next() {
        return counter += 2;

    }

    public Long getCounter() {
        return counter;
    }

    /**
     * Test your solution here by calling {@link #next()} from different threads.
     *
     * @param args Input arguments to the main method. Unused.
     */
    public static void main(String[] args) {
        Even even = new Even();
        Runnable runnable = () -> {
            even.next();
            System.out.printf("Number: %s \n", even.getCounter());
            if (even.getCounter() % 2 == 1) {
                System.out.printf("--------------------------: ERROR at %s \n", even.getCounter());
            }
        };
        Even evenTwo = new Even();
        Runnable runnableTwo = () -> {
            evenTwo.next();
            System.out.printf("Number: %s \n", evenTwo.getCounter());
            if (evenTwo.getCounter() % 2 == 1) {
                System.out.printf("--------------------------: ERROR at %s \n", evenTwo.getCounter());
            }
        };
        List<Thread> threadList = new ArrayList<>();
        while (even.hasNext()) {

            Thread thread = new Thread( runnable );
            Thread threadTwo = new Thread( runnableTwo );
            thread.start();
            threadTwo.start();
            threadList.add( thread );
            threadList.add( threadTwo );

        }
    }

}
