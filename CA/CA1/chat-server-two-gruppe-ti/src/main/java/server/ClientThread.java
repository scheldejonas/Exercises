package server;

import java.io.*;
import java.net.Socket;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ClientThread extends ChatServer implements Runnable {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ClientThread(Socket socket) {

        this.socket = socket;

    }

    @Override
    public void run() {

        try {

            out = new PrintWriter(socket.getOutputStream(),true);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.printf("Socket is connected: %s \n",socket.isConnected() );
            while (socket.isConnected()) {

                if (in.ready()) {

                    String input = in.readLine();

                    if (input != null) {
                        System.out.println("Server recieved message from client: " +  input);

                        filterMessageThroughProtocolAndSend(input);

                    }
                }

                if (this.getOut().checkError()) {
                    System.out.println("Checking error with client: " + this.getOut().checkError());

                    ClientThread removeClientThread = null;

                    for (ClientThread clientThread : clientThreadList) {
                        if (clientThread.getUsername().equals(this.getUsername())) {
                            removeClientThread = clientThread;
                        }
                    }

                    clientThreadList.remove(removeClientThread);

                    for (ClientThread clientThread : clientThreadList) {

                        clientThread.getOut().println("DELETE#" + this.username);

                    }

                }

            }

        } catch (IOException e) {
            System.err.println("Input or output error, when relay messages from client on server");
            e.printStackTrace();
        }

    }

    private void filterMessageThroughProtocolAndSend(String recievedText) {

        String commandTextLine = recievedText.substring(0,recievedText.indexOf('#',0));
        System.err.printf("Incoming commandTextLine: %s \n", commandTextLine);

        String parameterAndMessageTextLine = recievedText.substring(recievedText.indexOf('#',0) + 1);
        System.err.printf("Incoming parameterAndMessageTextLine: %s \n", parameterAndMessageTextLine);

        if (  commandTextLine.equals("LOGIN") ) {

            String username = recievedText.substring(recievedText.lastIndexOf('#') + 1);
            this.username = username;
            System.out.printf("LOGIN command for: %s \n", username);
            String activeUsers = "";

            for (ClientThread clientThread : clientThreadList) {

                clientThread.getOut().println("UPDATE#" + username);

                activeUsers += clientThread.getUsername() + "#";

            }

            this.getOut().printf("OK#%s\n",activeUsers);
        }

        if ( commandTextLine.equals("MSG") ) {

            String parameterTextLine = parameterAndMessageTextLine.substring(0,parameterAndMessageTextLine.indexOf('#',0));
            System.out.printf("Incoming parameterTextLine: %s \n", parameterTextLine);

            String messageTextLine = parameterAndMessageTextLine.substring(parameterAndMessageTextLine.lastIndexOf('#') + 1);
            System.out.printf("Incoming messageTextLine: %s \n", messageTextLine);

            if (parameterTextLine.equals("ALL")) {
                System.out.println("Sending message to all clients");

                for (ClientThread clientThread : clientThreadList) {

                    clientThread.getOut().println("MSG#" + this.username + "#" + messageTextLine);

                }
            }

            if (!parameterTextLine.equals("ALL")) {
                System.out.println("Sending message privatly to specific user");

                this.getOut().println("MSG#" + parameterTextLine + "#" + messageTextLine);

                for (ClientThread clientThread : clientThreadList) {

                    System.out.println("Username testet: " + clientThread.getUsername());
                    if (parameterTextLine.equals(clientThread.getUsername())) {

                        clientThread.getOut().println("MSG#" + clientThread.getUsername() + "#" + messageTextLine);
                        System.out.println("Message " + messageTextLine + " sending to " + clientThread.getUsername());

                    }

                }

            }

        }

    }

    public PrintWriter getOut() {

        return out;

    }

    public String getUsername() {
        return username;
    }
}
