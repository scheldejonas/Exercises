package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class SwingClientForm {
    private JButton jButtonSend;
    private JTextArea textAreaChat;
    private JTextField textFieldMessage;
    private JTextArea textAreaActiveUsers;
    private JPanel jPanelClient;

    public SwingClientForm() {
        jButtonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
