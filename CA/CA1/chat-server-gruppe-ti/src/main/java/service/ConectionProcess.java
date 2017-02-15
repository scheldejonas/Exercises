package service;

import service.ChatProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This ConenctionProcess is in seperate runnable, to make the server able to seperate connections on threads and park them in the ThreadFactory, to start when ressources are ready
 *
 * Created by scheldejonas on 15/02/2017.
 */
public class ConectionProcess implements Runnable {
    private final ReentrantLock reentrantLock;
    private Socket connectionSocket;

    public ConectionProcess(ReentrantLock reentrantLock, Socket connectionSocket) {
        this.reentrantLock = reentrantLock;
        this.connectionSocket = connectionSocket;
    }

    public void run() {
        try {
            reentrantLock.lock();

            InputStream inputStream = connectionSocket.getInputStream();

            BufferedReader packetBufferedInputStreamReader = new BufferedReader(new InputStreamReader(inputStream));

            String recievedTextMessage = packetBufferedInputStreamReader.readLine();

            ChatProtocol chatProtocol = new ChatProtocol();

            if (recievedTextMessage.contains("LOGIN")) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }


    }
}
