package client;

import protocol.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

import static protocol.Protocol.*;

/**
 * Created by schelde on 07/06/17.
 */
public class Client {
    private String host = "localhost";
    private int port = 8080;
    private int id = -1;
    private Socket socket = null;
    private PrintWriter printWriter = null;
    private BufferedReader bufferedReader = null;
    private boolean isTurned = false;
    private ReentrantLock reentrantLock;

    private int counter = 0;
    private boolean turned;

    public Client() {
    }

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connectToServer() throws IOException, InterruptedException {
        this.socket = new Socket(this.host, this.port);
        this.printWriter = new PrintWriter(this.socket.getOutputStream(),true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream(),"UTF8"));
        this.printWriter.println(Protocol.formatGetId("Turnstile"));
        while (this.socket.isConnected()) {
            if (this.bufferedReader.ready()) {
                reentrantLock.lock();
                String newCommand = this.bufferedReader.readLine();
                handleCommand(newCommand);
                reentrantLock.unlock();
            } else if (isTurned && id != -1) {
                reentrantLock.lock();
                this.counter++;
                System.out.println("...indending increment" + this.counter);
                String incrementCommandToServer = Protocol.formatIncrement("Turnstile", id);
                this.printWriter.println(incrementCommandToServer);
                this.isTurned = false;
                reentrantLock.unlock();
            }
        }
    }

    public void print(String message) {
        this.printWriter.println("Hello");
    }

    public void setTurned(boolean turned) {
        isTurned = turned;
    }

    // UNITID_COMMAND_VALUES
    public void handleCommand(String command) {
        String[] commandArray = command.split(Protocol.getSeparator());
        Protocol protocol = Protocol.findCommandProtocol(commandArray[1]);
        switch (protocol) {
            case INCREMENT_PERSON:
                break;
            case SET_ID:
                this.id = Integer.parseInt(commandArray[2]);
                break;
        }
    }

    public void setReentrantLock(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    public boolean isTurned() {
        return this.isTurned;
    }
}
