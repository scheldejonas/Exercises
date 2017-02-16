package client;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class Client {

    protected static ClientForm clientForm;
    protected static Socket socket;
    protected static ServerThread serverThread;
    protected static Thread server;

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("ClientForm");

        socket = null;

        clientForm = new ClientForm(jFrame, socket);

        jFrame.setContentPane(clientForm.getjPanelOne());

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.pack();

        jFrame.setVisible(true);

        while (clientForm.getUserName() == "") {
            try {

                Thread.sleep(500);
                System.out.println("TEST - Waiting for a User Name");

            } catch (InterruptedException e) {
                System.out.printf("Error while waiting for username \n");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }


        }

        String name = clientForm.getUserName();

        String host = "localhost";

        int portNumber = 8081;

        try {

            socket = new Socket();

            socket.connect(new InetSocketAddress(host,portNumber));
            System.out.printf("TEST - Connecting Client to Server on %s:%s...\n",host,portNumber);

            Thread.sleep(500);

            serverThread = new ServerThread(socket,name);

            Thread.sleep(500);

            server = new Thread(serverThread);

            server.start();
            System.out.printf("TEST - Starting Server connection as a Thread...\n");

        } catch (UnknownHostException e) {
            System.err.println("Unknown host error!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal Connection error!");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Thread got interrupted with this message: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
