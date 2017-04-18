package view;

import controller.ChatServerConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class SwingClientConnectForm {
    private JTextField textHostName;
    private JTextField textPortNumber;
    private JButton loginButton;
    private JPanel jPanelClientConnectForm;
    private JButton loginOnStandardButton;
    private JFrame jFrame = null;
    private static final SwingClientConnectForm singleton = new SwingClientConnectForm();

    private ChatServerConnection chatServerConnection = ChatServerConnection.getSingleton();
    private SwingClientForm swingClientForm = SwingClientForm.getSingleton();

    private SwingClientConnectForm() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("...User pushed the login button, and we are now trying to connect to the server, with typed in values.");
                chatServerConnection.setConnectionLocation(textHostName.getText(),Integer.parseInt(textPortNumber.getText()));
                chatServerConnection.connectClientToServer();
                swingClientForm.openAndShowClientGUI();
                singleton.jFrame.dispose();
            }
        });
        loginOnStandardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("...User pushed the standard login button.");
                chatServerConnection.setConnectionLocation("localhost", 8084);
                chatServerConnection.connectClientToServer();
                swingClientForm.openAndShowClientGUI();
                singleton.jFrame.dispose();
            }
        });
    }

    public static SwingClientConnectForm getSingleton() {
        return singleton;
    }

    public void openAndShowChatGUI() {
        jFrame = new JFrame("SwingClientConnectForm");
        SwingClientConnectForm swingClientConnectForm = new SwingClientConnectForm();
        jFrame.setContentPane(swingClientConnectForm.jPanelClientConnectForm);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
