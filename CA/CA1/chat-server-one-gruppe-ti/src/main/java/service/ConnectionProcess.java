package service;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This ConenctionProcess is in seperate runnable, to make the server able to seperate connections on threads and park them in the ThreadFactory, to start when ressources are ready
 *
 * Created by scheldejonas on 15/02/2017.
 */
public class ConnectionProcess implements Runnable {

    private final ReentrantLock reentrantLock;
    private Socket connectionSocket;

    public ConnectionProcess(ReentrantLock reentrantLock, Socket connectionSocket) {
        this.reentrantLock = reentrantLock;
        this.connectionSocket = connectionSocket;
    }

    public void run() {
        try {

            reentrantLock.lock();

            InputStream inputStream = connectionSocket.getInputStream();

            BufferedReader packetBufferedInputStreamReader = new BufferedReader(new InputStreamReader(inputStream));

            String recievedText = packetBufferedInputStreamReader.readLine();

            ChatProtocolImpl chatProtocolImpl = new ChatProtocolImpl();

            String requestServerResponse = chatProtocolImpl.handleRecievedLine(recievedText);

            OutputStream outputStream = connectionSocket.getOutputStream();

            PrintWriter responsePrintWriter = new PrintWriter(outputStream);

            responsePrintWriter.println(requestServerResponse);

            connectionSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }

}
