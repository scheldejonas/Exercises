package controller;

import java.net.ServerSocket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public class ConectionProcess implements Runnable {
    private final ReentrantLock reentrantLock;
    private ServerSocket serverSocket;

    public ConectionProcess(ReentrantLock reentrantLock, ServerSocket serverSocket) {
        this.reentrantLock = reentrantLock;
        this.serverSocket = serverSocket;
    }

    public void run() {
        try {
            reentrantLock.lock();

            

        } finally {
            reentrantLock.unlock();
        }


    }
}
