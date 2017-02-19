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
    private String username = null;
    private ChatProtocol chatProtocol = null;

    private ScannerChatUI scannerChatUI = null;

    public ServerConnectionThread(Socket clientSocket, String username) {
        this.clientSocket = clientSocket;
        this.username = username;
        try {
            Class.forName( "service.ChatProtocol" );
        } catch( ClassNotFoundException e ) {
            System.err.println("Chat Protocol Class is not implemented in this Package");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        this.chatProtocol = new ChatProtocolImpl();
    }

    public void run() {
        this.chatProtocol.setScannerChatIU(this.scannerChatUI);
        readyWritersAndReaders();
        System.out.printf("ChatClientThread socket is connected?: %s\n", clientSocket.isConnected());
        System.out.println("Starting to check server constantly for messages and run through protocol");
        while (!clientSocket.isClosed()) {
            waitOneMiliSeconds();
            checkServerAndPrintToClient();
        }
    }

    private void waitOneMiliSeconds() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.err.println("Thread sleeping was interrupted in ServerConnectionThread");
            e.printStackTrace();
        }
    }

    protected void printToServer(String readyTextLine) {
        this.printToServerStream.println(readyTextLine);
    }


    private void checkServerAndPrintToClient() {
        try {
            if (this.recieveFromServerStream.ready()) {
                System.err.println("Service recieved a messsage from server");
                chatProtocol.onClientAnalyseReceivedTextFromServer(this.recieveFromServerStream.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
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

