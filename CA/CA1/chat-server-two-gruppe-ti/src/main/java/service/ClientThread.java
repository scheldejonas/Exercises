package service;

import controller.ChatServer;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ClientThread implements Runnable {

    private Socket socket;
    private PrintWriter toClientPrintOutWriter;
    private BufferedReader receiveFromClientReaderInput;
    private String username = "";
    private ChatProtocolImpl chatProtocol;
    private ChatServer chatServer;

    /**
     * Instantiates a new Client thread.
     *
     * @param socket the socket
     */
    public ClientThread(Socket socket) {
        this.socket = socket;
        this.chatProtocol = new ChatProtocolImpl();
        this.chatProtocol.setClientThread(this);
    }

    @Override
    public void run() {
        try {
            toClientPrintOutWriter = new PrintWriter(socket.getOutputStream(),true);
            receiveFromClientReaderInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.printf("Socket is connected: %s \n",socket.isConnected() );
            while (!socket.isClosed()) {
                String input = receiveFromClientReaderInput.readLine();
                if (input != null) {
                    System.out.println("Server received message from client: " + input);
                    this.chatProtocol.onServerAnalyseReceivedTextFromClient(input);
                } else {
                    System.out.println("Checking error with client: " + this.getToClientPrintOutWriter().checkError());
                    this.getSocket().close();
                    this.chatProtocol.onServerSendRemoveUser(this.username);
                }
            }
        } catch (IOException e) {
            System.err.println("Input or output error, when relay messages from client on server");
            e.printStackTrace();
        }
    }

    /**
     * Gets to client print out writer.
     *
     * @return the to client print out writer
     */
    public PrintWriter getToClientPrintOutWriter() {
        return this.toClientPrintOutWriter;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets chat server.
     *
     * @return the chat server
     */
    public ChatServer getChatServer() {
        return this.chatServer;
    }

    /**
     * Sets chat server.
     *
     * @param chatServer the chat server
     */
    public void setChatServer(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets socket.
     *
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Sets socket.
     *
     * @param socket the socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Sets to client print out writer.
     *
     * @param toClientPrintOutWriter the to client print out writer
     */
    public void setToClientPrintOutWriter(PrintWriter toClientPrintOutWriter) {
        this.toClientPrintOutWriter = toClientPrintOutWriter;
    }

    /**
     * Gets receive from client reader input.
     *
     * @return the receive from client reader input
     */
    public BufferedReader getReceiveFromClientReaderInput() {
        return receiveFromClientReaderInput;
    }

    /**
     * Sets receive from client reader input.
     *
     * @param receiveFromClientReaderInput the receive from client reader input
     */
    public void setReceiveFromClientReaderInput(BufferedReader receiveFromClientReaderInput) {
        this.receiveFromClientReaderInput = receiveFromClientReaderInput;
    }

    /**
     * Gets chat protocol.
     *
     * @return the chat protocol
     */
    public ChatProtocolImpl getChatProtocol() {
        return chatProtocol;
    }

    /**
     * Sets chat protocol.
     *
     * @param chatProtocol the chat protocol
     */
    public void setChatProtocol(ChatProtocolImpl chatProtocol) {
        this.chatProtocol = chatProtocol;
    }
}
