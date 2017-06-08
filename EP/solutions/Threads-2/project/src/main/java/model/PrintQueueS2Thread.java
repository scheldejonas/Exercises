package model;

/**
 * Created by schelde on 08/06/17.
 */
public class PrintQueueS2Thread extends Thread {
    private long totalResultOfCalculatedFibo = 0;

    @Override
    public void run() {
        //System.out.println("...starting PrintQueueS2Thread");
        while (true) {
            //System.out.println("...inside while looop of PrintQueueS2Thread");
            if (!Fibonacci.getBlockingQueueS2().isEmpty()) {
                try {
                    //System.out.println("...inside try of PrintQueueS2Thread");
                    Long longNewCalculated = Fibonacci.getBlockingQueueS2().take();
                    //System.out.println("...took number from queue in PrintQueueS2Thread");
                    this.totalResultOfCalculatedFibo += longNewCalculated;
                    System.out.println("...PrintQueueS2Thread | new fibonacci calculated; " + longNewCalculated);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (this.isInterrupted()) {
                System.out.println("...PrintQueueS2Thread | total sum of fibonacci calculated to: " + totalResultOfCalculatedFibo);
                break;
            }
        }
    }
}
