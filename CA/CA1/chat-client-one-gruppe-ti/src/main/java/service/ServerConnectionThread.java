package service;

import controller.ChatClientThread;
import view.ScannerChatUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ServerConnectionThread implements Runnable {

    private PrintWriter printToServerStream = null;
    private BufferedReader recieveFromServerStream = null;
    private Socket clientSocket = null;
    private ChatClientThread chatClientThread = null;
    private ChatProtocolImpl chatProtocol = null;
    private ScannerChatUI scannerChatUI = null;

    public ServerConnectionThread(Socket clientSocket, ChatClientThread chatClientThread) {
        this.clientSocket = clientSocket;
        this.chatClientThread = chatClientThread;
        try {
            Class.forName( "service.ChatProtocol" );
        } catch( ClassNotFoundException e ) {
            System.err.println("Chat Protocol Class is not implemented in this Package");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        this.chatProtocol = new ChatProtocolImpl();
        this.chatProtocol.setChatClientThread(this.chatClientThread);
    }

    @Override
    public void run() {
        this.chatProtocol.setScannerChatUI(this.scannerChatUI);
        readyWritersAndReaders();
        System.out.printf("ChatClientThread socket is connected?: %s\n", clientSocket.isConnected());
        loginToServerChat(this.chatClientThread.getUsername());
        System.out.println("Starting to check server constantly for messages and run through protocol");
        while (!clientSocket.isClosed()) {
            waitOneMiliSeconds();
            checkServerAndPrintToClient();
        }
    }

    private void readyWritersAndReaders() {
        try {
            this.printToServerStream = new PrintWriter(clientSocket.getOutputStream(), true);
            this.recieveFromServerStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginToServerChat(String username) {
        String loginTextToServer = chatProtocol.onClientPrepareLoginToServer(username);
        printToServer(loginTextToServer);
    }

    private void waitOneMiliSeconds() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.err.println("Thread sleeping was interrupted in ServerConnectionThread");
            e.printStackTrace();
        }
    }

    private void checkServerAndPrintToClient() {
        try {
            if (this.recieveFromServerStream.ready()) {
                this.chatProtocol.onClientAnalyseReceivedTextFromServer(this.recieveFromServerStream.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printToServer(String readyTextLine) {
        this.printToServerStream.println(readyTextLine);
    }

    public ChatProtocol getChatProtocol() {
        return chatProtocol;
    }

    public ScannerChatUI getScannerChatUI() {
        return scannerChatUI;
    }

    public void setScannerChatUI(ScannerChatUI scannerChatUI) {
        this.scannerChatUI = scannerChatUI;
    }
}

