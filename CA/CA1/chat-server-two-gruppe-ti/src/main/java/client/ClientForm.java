package client;

import com.sun.tools.javah.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ClientForm {
    private final JFrame jFrame;
    private JButton sendButton;
    private JTextField messageField;
    private JPanel jPanelOne;
    private JTextPane chatArea;
    private JButton logOut;
    private String userName = "";

    public ClientForm(JFrame jFrame) {

        this.jFrame = jFrame;

        sendButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (userName == "") {

                    userName = messageField.getText();

                    String text = chatArea.getText();

                    text += String.format("\n Welcome to %s\n" +
                            "We will inform you when new users is entering the chat int he list to your right", userName);

                    chatArea.setText(text);

                } else {

                    String text = chatArea.getText();

                    System.out.printf("%s\n",messageField.getText());
                    System.out.printf("Printing to System out from Client Form \n");

                    text += String.format("\n %s", messageField.getText());

                    messageField.setText("");

                    chatArea.setText(text);

                }



            }

        });

        logOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));

                System.exit(1);

            }

        });

    }

    public JPanel getjPanelOne() {
        return jPanelOne;
    }

    public String getUserName() {
        return this.userName;
    }
}
