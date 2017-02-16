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

    public static void main(String[] args) {

        JFrame jFrame = new JFrame("ClientForm");

        ClientForm clientForm = new ClientForm(jFrame);

        jFrame.setContentPane(clientForm.getjPanelOne());

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.pack();

        jFrame.setVisible(true);

        while (clientForm.getUserName() == "") {
            try {

                Thread.sleep(500);

            } catch (InterruptedException e) {
                System.out.printf("Error while waiting for username \n");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            System.out.println("TEST - Waiting for a User Name");

        }

        String name = clientForm.getUserName();

        String host = "localhost";

        int portNumber = 8081;

        try {

            Socket socket = new Socket();

            socket.connect(new InetSocketAddress(host,portNumber));
            System.out.printf("TEST - Connecting Client to Server on %s:%s...\n",host,portNumber);

            Thread.sleep(1000);

            Thread server = new Thread(new ServerThread(socket,name));

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
