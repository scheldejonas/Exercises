package view;

import javax.swing.*;
import java.io.*;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class Application {

    public static void main(String[] args) {
        new Application(); //Starts this program (Check constructor below for more details on how it do)
    }

    private String pathForServiceJar = "";
    private File file = null;
    private ClientChat clientChatWindow = null;
    private Process serviceProcess = null;
    private BufferedReader readerFromService = null;
    private BufferedReader errorFromService = null;
    private PrintWriter printerToService = null;

    public Application() {
        startGUI();
        clientChatWindow.pointTheChatClientService();
        startServiceForChatAndReadyReaderAndPrinterToService();
    }

    public void startGUI() {
        JFrame jFrame = new JFrame("ClientChat");
        clientChatWindow = new ClientChat(jFrame, this);
        jFrame.setContentPane(clientChatWindow.getjPanelChat());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private void startServiceForChatAndReadyReaderAndPrinterToService() {
        try {
            serviceProcess = Runtime.getRuntime().exec(String.format("java -jar %s", this.pathForServiceJar));
        } catch (IOException e) {
            e.printStackTrace();
        }
        readerFromService = new BufferedReader(new InputStreamReader(serviceProcess.getInputStream()));
        errorFromService = new BufferedReader(new InputStreamReader(serviceProcess.getErrorStream()));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String output = null;
                    System.out.println("GUI: Showing service System.out here as well.");
                    while (serviceProcess.isAlive()) {
                        if (readerFromService.ready()) {
                            appendChatArea(readerFromService.readLine());
                        }
                        if (errorFromService.ready()) {
                            System.out.println("Service: " + errorFromService.readLine());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        printerToService = new PrintWriter(serviceProcess.getOutputStream(), true);
    }

    private void appendChatArea(String recievedText) {
        String becomingNewTextAra = clientChatWindow.getChatArea().getText();
        becomingNewTextAra += String.format("\n%s",recievedText);
        clientChatWindow.getChatArea().setText(becomingNewTextAra);
    }

    public String getPathForServiceJar() {
        return pathForServiceJar;
    }

    public void setPathForServiceJar(String pathForServiceJar) {
        this.pathForServiceJar = pathForServiceJar;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ClientChat getClientChatWindow() {
        return clientChatWindow;
    }

    public Process getServiceProcess() {
        return serviceProcess;
    }

    public BufferedReader getReaderFromService() {
        return readerFromService;
    }

    public PrintWriter getPrinterToService() {
        return printerToService;
    }
}
