import client.Client;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by schelde on 07/06/17.
 */
public class ApplicationClient {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock(true);
        final Client client = new Client();
        client.setReentrantLock(reentrantLock);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ExecutorService executorService = Executors.newFixedThreadPool(2,threadFactory);
        Thread thread = new Thread(new Runnable() {
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 1000; i++) {
                    while (client.isTurned()) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("...going to lock");
                    reentrantLock.lock();
                    System.out.println("...running inside lock application client");
                    client.setTurned(true);
                    reentrantLock.unlock();
                }
            }
        });
        executorService.execute(thread);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    client.connectToServer();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
