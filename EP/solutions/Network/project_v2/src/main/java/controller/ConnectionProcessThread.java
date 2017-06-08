package controller;

import model.Database;
import model.StadiumProtocol;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 06/02/17.
 */
public class ConnectionProcessThread extends Thread {
    private model.StadiumProtocol.Unit unit = null;
    private BufferedReader bufferedReader = null;
    private StadiumProtocol stadiumProtocol = StadiumProtocol.getSingleton();
    private Database database = Database.getSingleton();
    private PrintWriter writer = null;

    public ConnectionProcessThread() {
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (bufferedReader.ready()) {
                    String recievedLine = bufferedReader.readLine();
                    System.out.println("...Received this text from the client: " + recievedLine);
                    model.StadiumProtocol.Commands command = stadiumProtocol.getMessageReturnCommand(recievedLine);
                    System.out.println("...Received this command from unit: " + command.name());
                    boolean isCorrectCommandName = false;
                    if (command.equals(model.StadiumProtocol.Commands.ADD_PERSON) && unit.getClass().getSimpleName().equals("Turnstile") ) {
                        isCorrectCommandName = true;
                        long totalPeople = database.addOnePerson(unit.getId());
                        System.out.println("...Added one person to database and it has now: " + totalPeople + " total people.");
                    }
                    if (command.equals(model.StadiumProtocol.Commands.ECHO_TOTAL_PEOPLE_COUNT) && unit.getClass().getSimpleName().equals("Monitor")) {
                        isCorrectCommandName = true;
                        long totalPeople = database.getPeople();
                        sendMessage("The total amount of people is: " + totalPeople);
                    }
                    if (command.equals(model.StadiumProtocol.Commands.ECHO_ALL_UNITS_WITH_COUNT) && unit.getClass().getSimpleName().equals("Monitor")) {
                        isCorrectCommandName = true;
                        for (Map.Entry<Long, AtomicLong> unit : database.getUnits().entrySet()
                             ) {
                            sendMessage("Unit ID: " + unit.getKey() + ", People Count: " + unit.getValue() + ".");
                        }
                    }
                    if (!isCorrectCommandName && unit.getClass().getSimpleName().equals("Monitor")) {
                        sendMessage("This is not the correct command for any action on the server. Please try again.");
                    }
                }
            } catch (IOException e) {
                System.out.println("...Error occured when trying to read line from client in ConnectionProcessThread");
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        System.out.println("...Writing a message: " + message + " to client.");
        writer.println(message);
        writer.flush();
        System.out.println("...Done writing message to client.");
    }

    private void defineAndSetTheConnecterUnit(Socket socket) {
        try {
            String connectionString = null;
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            connectionString = bufferedReader.readLine();
            System.out.println("...Server read line from initial posting: " + connectionString);
            unit = stadiumProtocol.getUnitObject(connectionString);
            System.out.println("...Server recieved connection from a " + unit.toString());
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
            System.out.println("...Server is setup with printer, for sending messages to client.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readyProcesWithSocket(Socket connectionSocket) {
        defineAndSetTheConnecterUnit(connectionSocket);
    }
}
