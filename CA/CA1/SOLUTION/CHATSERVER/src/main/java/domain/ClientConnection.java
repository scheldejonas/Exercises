package domain;

import service.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class ClientConnection extends Client implements Runnable {
    private Socket socket;
    private PrintWriter printLinesToClient;
    private BufferedReader receiveLinesFromClient;
    private ReentrantLock lock = null;

    private ClientService clientService = ClientService.getSingleton();

    public ClientConnection() {
    }

    @Override
    public void run() {
        while (!this.socket.isClosed()) {
            String newRecievedText = "";
            try {
                newRecievedText = receiveLinesFromClient.readLine();
            } catch (IOException e) {
                lock.unlock();
                System.err.println("...There was an error, while trying to read a new line from the client with reference: " + this.socket.hashCode());
                e.printStackTrace();
            }
            try {
                if (newRecievedText != null) {
                    lock.lock();
                    System.out.println("...Server recieved a text line from reference: " + this.socket.hashCode() + ", looking like this: " + receiveLinesFromClient);
                    clientService.newTextFromClient(newRecievedText, this);
                    System.out.println("...The ClientConnection are connected with this socket: ");
                    System.out.println(this.socket.toString());
                    lock.unlock();
                } else {
                    lock.lock();
                    System.out.println("...There was an error with the clients received text, and we are therefore closing the connection.");
                    try {
                        this.socket.close();
                    } catch (IOException e) {
                        lock.unlock();
                        System.err.println("...While trying to close the socket after an active client has recieved text, it was not possible to close the socket.");
                        e.printStackTrace();
                    }
                    ClientService.getSingleton().shutdownClientConnection(this);
                    lock.unlock();
                }
            } catch (Exception exception) {
                lock.unlock();
                System.err.println("...A small error happened when handling text throught the client connection.");
            }
        }
    }

    public void mountConnection(Socket newSocket) {
        this.socket = newSocket;
        try {
            this.printLinesToClient = new PrintWriter(this.socket.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("...There was an error, while trying to mount a print stream, for sending messages to the client.");
            try {
                this.socket.close();
            } catch (IOException e1) {
                System.out.println("...During error on trying to mount print stream, on new client, it was not possible to close client socket stream.");
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        try {
            this.receiveLinesFromClient = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("...There was an error, while trying to mount the input stream for recieveing text, from the client.");
            try {
                this.socket.close();
            } catch (IOException e1) {
                System.out.println("...During error on trying to mount input stream, on new client, it was not possible to close client socket.");
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        System.out.println("...A new client with reference: " + this.socket.hashCode() + " is connected.");
    }

    public void setLock(ReentrantLock reentrantLock) {
        this.lock = reentrantLock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientConnection that = (ClientConnection) o;

        return socket != null ? socket.equals(that.socket) : that.socket == null;
    }

    @Override
    public int hashCode() {
        return socket != null ? socket.hashCode() : 0;
    }

    public void sendText(String message) {
        try {
            lock.lock();
            System.out.println("...Sending text to reference: " + this.hashCode() + ", with username: " + this.getUser().getName() + " as: " + message);
            printLinesToClient.println(message);
            lock.unlock();
        } catch (Exception exception) {
            lock.unlock();
            System.out.println("...There was an error trying to sent text to reference: " + this.getUser().getName());
        }
    }

    @Override
    public String toString() {
        return "ClientConnection{" +
                "socket=" + socket.toString() +
                "user=" + this.getUser().toString() +
                '}';
    }
}
