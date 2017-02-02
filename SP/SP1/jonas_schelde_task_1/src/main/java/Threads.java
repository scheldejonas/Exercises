import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * <h2>Task 1</h2>
 * <p>
 * Write three methods:
 * <ol>
 * <li>Compute and print the sum of all numbers from 1 to 1 billion.</li>
 * <li>
 * Print the numbers from 1 to 5. Pause for 2 seconds between each print.
 * </li>
 * <li>
 * Print all numbers from 10 and up to {@link Integer#MAX_VALUE}. Pause for 3
 * seconds between each print.
 * </li>
 * </ol>
 * Create three threads that run each of the above methods. Start them all
 * simultaneously from your main method. Stop / kill the thread running the
 * third method after waiting 10 seconds.
 * </p>
 */
public class Threads {

    public static void printNumberToScreen(Integer number) {
        System.out.printf("%s \n",number);
    }

    public static void loopAndPrintComputedNumberFromOneToOneBillion() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                boolean isCounterBelowOneBillion = true;
                Integer counter = 0;
                Integer computedNumber = 0;

                while (isCounterBelowOneBillion) {
                    computedNumber = computeNumberWithCounter(computedNumber, counter);
                    counter++;
                    if (counter > 1000000) {
                        isCounterBelowOneBillion = false;
                    }
                }
                printNumberToScreen(computedNumber);
            }
        }
        thread.start();
    }


    private static Integer computeNumberWithCounter(Integer computedNumber, Integer counter) {
        return computedNumber + counter;
    }

    private static void startThreadWithSleep(int number) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.printf("Hi %s \n",number);
                    Thread.sleep(10000);
                    System.out.printf("Done sleeping %s \n", number);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    /**
     * Print all numbers from 10 and up to {@link Integer#MAX_VALUE}. Pause for 3
     * seconds between each print.
     */
    public static void printAllNumbersFromTenUpToIntegerMax() {
        Thread thread;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Integer integer = 0; integer < integer.MAX_VALUE; integer++) {
                    printNumberToScreen(integer);
                    pauseFor(3);
                }
            }
        };
        thread.start();
    }

    private static void pauseFor(Integer seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts three threads that execute three methods simultaneously.
     *
     * @param args Input arguments to the main method. Unused.
     */
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++ ) {
            startThreadWithSleep(i);
            loopAndPrintComputedNumberFromOneToOneBillion();
            printAllNumbersFromTenUpToIntegerMax();
        }
    }
}
