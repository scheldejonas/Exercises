package server;

import protocol.Protocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by schelde on 07/06/17.
 */
public class Server {
    private String host = "localhost";
    private int port = 8080;
    private ServerSocket serverSocket = null;
    private List<Socket> socketList = new ArrayList<Socket>();
    private PrintWriter printWriter = null;
    private List<Integer> countTurnstileList = new ArrayList<Integer>();

    public Server() {
    }

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void startServer() throws IOException {
        this.serverSocket = new ServerSocket(this.port);

        Socket socket = null;
        while (true) {
            socket = this.serverSocket.accept();
            System.out.println("...we have received a connection on socket: " + socket.toString());
            socketList.add(socket);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF8"));
            this.printWriter = new PrintWriter(socket.getOutputStream(),true);
            while (true) {
                while (bufferedReader.ready()) {
                    String textLine = bufferedReader.readLine();
                    System.out.println("...we received this text from a client: " + textLine);
                    handleCommand(textLine);
                }
            }
        }
    }

    // UNITID_COMMAND_VALUES
    public void handleCommand(String command) {
        System.out.println("...we tried to understand command: " + command);
        String[] commandArray = command.split(Protocol.getSeparator());
        Protocol protocol = Protocol.findCommandProtocol(commandArray[1]);
        switch (protocol) {
            case INCREMENT_PERSON:
                int id = Integer.parseInt(commandArray[0].replace("TURNSTILE","")) - 1;
                int tempCount = countTurnstileList.get(id);
                countTurnstileList.set(id, tempCount + 1);
                System.out.println("...turnstile" + id + " was turned to total count: " + tempCount);
                break;
            case SET_ID:
                printWriter.println("");
                break;
            case GET_ID:
                id = this.socketList.size();
                countTurnstileList.add(0);
                String formattetCommand = Protocol.formatSetId(id, "Server");
                printWriter.println(formattetCommand);
                break;
        }
    }
}