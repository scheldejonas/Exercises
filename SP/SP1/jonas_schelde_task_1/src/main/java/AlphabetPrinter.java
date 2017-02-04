import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <h2>Task 6</h2>
 * <p>
 * The {@link AlphabetPrinter} is a {@link Runnable} which simply prints the alphabet.
 * Your task is to implement the {@link #run()} method to print the alphabet
 * (in the {@link #ALPHABET} constant) one character at the time, one
 * line per character. In the {@link #main(String[])} method you should create
 * four {@link AlphabetPrinter}s, assign them to 4 different threads and start them all
 * simultaneously.
 * <br/>
 * Before you run them, what do you expect will print? Did you see what you
 * expected?
 * </p>
 */
public class AlphabetPrinter implements Runnable {

    // The alphabet to print
    private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final ReentrantLock reentrantLock;

    public AlphabetPrinter() {
        reentrantLock = new ReentrantLock();
    }

    /**
     * This method should print the alphabet one line at the time. Each line
     * should only contain one letter.
     */
    @Override
    public void run() {

        reentrantLock.lock();
        System.out.printf("Lock status: %s \n", reentrantLock.isLocked() );
        System.out.printf("STARTING alphabet printing... \n");
        for (char character : ALPHABET.toCharArray()
             ) {
            System.out.printf( "%s \n",character );
        }
        System.out.printf("DONE alphabet printing... \n");
        reentrantLock.unlock();
        System.out.printf("Lock status: %s \n", reentrantLock.isLocked());

    }

    /**
     * A executor service that could do starvation up to 12-13.000 threads executed at the same time. at submit for executor service
     *
     * it lasted up to 70-75.000 threads executed at the same time. at submit for executor service.
     *
     * Next question is then, how to make the qeue become a database, so it just re engage the port saved in the list in the db, do the task and answer back the user?
     *
     * @param args
     */
    public static void main(String[] args) {

        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);

        long startTime = System.currentTimeMillis();

        AlphabetPrinter alphabetPrinter = new AlphabetPrinter();

        int counter = 0;
        for (int i = 0; i < 80000; i++) {
            System.out.printf("Threads started: %s \n", counter++);
            executorService.execute(alphabetPrinter);
        }

        executorService.shutdown();

        long endTime = System.currentTimeMillis();

        long totalTime = endTime - startTime;

        System.out.printf("Time it took to run this program: %s \n",totalTime);

    }

//    /**
//     * Runs the program, from the lock in use, starting 2000 threads, before it want starved
//     *
//     * If using the lock - the runtime is 6-800 milisec
//     *
//     * if not using the lock - the runtime is 15-1800 milisec
//     *
//     * @param args
//     * @throws InterruptedException
//     */
//    public static void main(String[] args) throws InterruptedException {
//        long startTime = System.currentTimeMillis();
//
//        List<Thread> threadList = new ArrayList<>();
//
//        AlphabetPrinter alphabetPrinter = new AlphabetPrinter();
//
//        for (int i = 0; i < 2000; i++) {
//            threadList.add(new Thread(alphabetPrinter));
//        }
//
//        System.out.printf("Ready to start threads \n");
//        threadList.forEach(Thread::start);
//        System.out.printf("Done starting threads \n");
//
//        for (Thread thread : threadList
//             ) {
//            thread.join();
//        }
//
//        long endTime = System.currentTimeMillis();
//
//        long totalTime = endTime - startTime;
//
//        System.out.printf("Time it took to run this program: %s \n",totalTime);
//    }

//    public static void main(String[] args) throws InterruptedException {
//
//        long startTime = System.currentTimeMillis();
//
//        AlphabetPrinter alphabetPrinter = new AlphabetPrinter();
//
//        Thread thread = new Thread(alphabetPrinter);
//        Thread threadTwo = new Thread(alphabetPrinter);
//        Thread threadThree = new Thread(alphabetPrinter);
//        Thread threadFour = new Thread(alphabetPrinter);
//
//        System.out.printf("Ready to start threads \n");
//        thread.start();
//        threadTwo.start();
//        threadThree.start();
//        threadFour.start();
//        System.out.printf("Done starting threads \n");
//
//        thread.join();
//        threadTwo.join();
//        threadThree.join();
//        threadFour.join();
//
//        long endTime = System.currentTimeMillis();
//
//        long totalTime = endTime - startTime;
//
//        System.out.printf("Time it took to run this program: %s \n",totalTime);
//
//    }

//    /**
//     * Creates four threads and start them simultaneously.
//     *
//     * EXPECTATION: here was that the alphabet, then would be printed in one alphabet at a time, but still didn't seem to work.
//     *
//     * CONCLUSION: is, it is difficult to insure synchronus threading in your java program, and needs to be focused
//     *
//     * @param args Input arguments to the main method. Unused.
//     */
//
//    public static void main(String[] args) {
//        Thread thread = new Thread( () -> {
//                AlphabetPrinter alphabetPrinter = new AlphabetPrinter();
//                alphabetPrinter.run();
//            }
//        );
//        Thread threadTwo = new Thread( () -> {
//                AlphabetPrinter alphabetPrinter = new AlphabetPrinter();
//                alphabetPrinter.run();
//            }
//        );
//        Thread threadThree = new Thread( () -> {
//                AlphabetPrinter alphabetPrinter = new AlphabetPrinter();
//                alphabetPrinter.run();
//            }
//        );
//        Thread threadFour = new Thread( () -> {
//                AlphabetPrinter alphabetPrinter = new AlphabetPrinter();
//                alphabetPrinter.run();
//            }
//        );
//        List<Thread> threadList = new ArrayList<>();
//        threadList.add(thread);
//        threadList.add(threadTwo);
//        threadList.add(threadThree);
//        threadList.add(threadFour);
//        System.out.printf("Size of thread list: %s \n", threadList.size() );
//        threadList.forEach(Thread::start);
//        threadList.forEach((thread1) -> {
//            try {
//                thread1.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }

//    /**
//     * The expectations here was that the alphabet, then would be printed in one alphabet at a time.
//     *
//     * The conclusion is, it is difficult to insure synchronus threading in your java program, and needs to be focused
//     * @param args
//     */
//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        Runnable runnable = () -> {
//            AlphabetPrinter printer = new AlphabetPrinter();
//            printer.run();
//        };
//        executorService.execute(runnable);
//        executorService.execute(runnable);
//        executorService.execute(runnable);
//        executorService.execute(runnable);
//    }


}