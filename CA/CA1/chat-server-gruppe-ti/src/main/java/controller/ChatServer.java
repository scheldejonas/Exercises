package controller;

import domain.User;
import service.ConectionProcess;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public class ChatServer implements Runnable {

    private final ReentrantLock reentrantLock;
    private ThreadFactory threadFactory;
    private ExecutorService executorService;
    private ServerSocket serverSocket;

    public ChatServer(ReentrantLock reentrantLock) throws IOException, LockMissingException {
        if (reentrantLock != null) {
            this.reentrantLock = reentrantLock;
        } else {
            throw new LockMissingException();
        }
        this.serverSocket = new ServerSocket();
        this.threadFactory = Executors.defaultThreadFactory();
        this.executorService = Executors.newFixedThreadPool(128,threadFactory);
    }

    public void run() {

        checkServerIsBound();

        System.out.printf("Server listening on %s:%s \n", serverSocket.getInetAddress().getHostName(), serverSocket.getLocalPort());

        System.out.printf("Waiting... \n");

        Socket connectionSocket;

        try {
            while ( (connectionSocket = serverSocket.accept()) != null ) {

                executorService.execute(new ConectionProcess(reentrantLock,connectionSocket));

            }
        } catch (IOException e) {
            reentrantLock.unlock();
            executorService.shutdown();
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }

    public void bindServerToNet(String host, int port) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(host,port);
        this.serverSocket.bind(inetSocketAddress);
    }

    private void checkServerIsBound() {
        if (!serverSocket.isBound()) {
            try {
                throw new ServerNotBoundExecption();
            } catch (ServerNotBoundExecption serverNotBoundExecption) {
                serverNotBoundExecption.printStackTrace();
                System.out.println(serverNotBoundExecption.getMessage());
            }
        }
    }
}
