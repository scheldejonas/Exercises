package client;

import com.sun.tools.javah.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ClientForm extends Client {
    private final JFrame jFrame;
    private JButton sendButton;
    private JTextField messageField;
    private JPanel jPanelOne;
    private JTextPane chatArea;
    private JButton logOut;
    private String userName = "";

    public ClientForm(JFrame jFrame, Socket socket) {

        this.jFrame = jFrame;

        sendButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (userName == "") {

                    userName = messageField.getText();

                    emptyTypingField();

                    appendTextAreaWithMessage(userName, "Welcome to %s\n" +
                            "We will inform you when new users is entering the chat int he list to your right");

                } else {

                    appendTextAreaWithMessage( getUserName(),messageField.getText());

                    emptyTypingField();

                }

            }

        });

        logOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));

                try {

                    serverThread.getSocket().close();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {

                    Thread.sleep(500);

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                server.interrupt();

            }

        });

    }

    protected void emptyTypingField() {

        messageField.setText("");
        System.out.println("TEST: Emptied the inputfield");

    }

    protected void appendTextAreaWithMessage(String userName, String newMessage) {

        String presentTextAreaContent = chatArea.getText();

        presentTextAreaContent += String.format("%s > %s\n", userName, newMessage);

        chatArea.setText(presentTextAreaContent);

    }

    public JPanel getjPanelOne() {
        return jPanelOne;
    }

    public String getUserName() {
        return this.userName;
    }

}
