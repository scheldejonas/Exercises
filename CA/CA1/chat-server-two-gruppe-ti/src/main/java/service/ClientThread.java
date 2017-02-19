package service;

import controller.ChatServer;

import java.io.*;
import java.net.Socket;
import java.util.List;

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
                if (receiveFromClientReaderInput.ready()) {
                    String input = receiveFromClientReaderInput.readLine();
                    if (input != null) {
                        System.out.println("Server received message from client: " +  input);
                        this.chatProtocol.onServerAnalyseReceivedTextFromClient(input);
                    }
                }
                if (isConnectionBroken()) {
                    System.out.println("Checking error with client: " + this.getToClientPrintOutWriter().checkError());
                    this.chatProtocol.onServerSendRemoveUser(this.username);
                }
            }
        } catch (IOException e) {
            System.err.println("Input or output error, when relay messages from client on server");
            e.printStackTrace();
        }
    }

    private boolean isConnectionBroken() {
        try {
            if (-1 == this.receiveFromClientReaderInput.read()) {
                this.socket.close();
                throw new IOException();
            };
            return false;
        } catch (IOException e) {
            System.out.println("User connection is broken");
            return true;
        }
    }

    public PrintWriter getToClientPrintOutWriter() {
        return this.toClientPrintOutWriter;
    }

    public String getUsername() {
        return this.username;
    }

    public ChatServer getChatServer() {
        return this.chatServer;
    }

    public void setChatServer(ChatServer chatServer) {
        this.chatServer = chatServer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setToClientPrintOutWriter(PrintWriter toClientPrintOutWriter) {
        this.toClientPrintOutWriter = toClientPrintOutWriter;
    }

    public BufferedReader getReceiveFromClientReaderInput() {
        return receiveFromClientReaderInput;
    }

    public void setReceiveFromClientReaderInput(BufferedReader receiveFromClientReaderInput) {
        this.receiveFromClientReaderInput = receiveFromClientReaderInput;
    }

    public ChatProtocolImpl getChatProtocol() {
        return chatProtocol;
    }

    public void setChatProtocol(ChatProtocolImpl chatProtocol) {
        this.chatProtocol = chatProtocol;
    }
}
