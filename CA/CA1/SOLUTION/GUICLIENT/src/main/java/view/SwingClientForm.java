package view;

import controller.ChatServerConnection;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.tools.internal.xjc.reader.Ring.add;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class SwingClientForm {
    private JButton jButtonSend;
    private JTextArea textAreaChat;
    private JTextField textFieldMessage;
    private JTextArea textAreaActiveUsers;
    private JPanel jPanelClient;
    private JList<String> listActiveUsers;
    private DefaultListModel<String> model;
    private JFrame jFrame = null;
    private static final SwingClientForm singleton = new SwingClientForm();

    private ChatServerConnection chatServerConnection = null;

    private SwingClientForm() {
        jButtonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatServerConnection = ChatServerConnection.getSingleton();
                chatServerConnection.sentTextToServer("MSG#ALL#" + textFieldMessage.getText());
                textFieldMessage.setText("");
            }
        });
    }

    public static SwingClientForm getSingleton() {
        return singleton;
    }

    public void openAndShowClientGUI() {
        jFrame = new JFrame("SwingClientForm");
        SwingClientForm swingClientConnectForm = new SwingClientForm();
        jFrame.setContentPane(swingClientConnectForm.jPanelClient);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
        chatServerConnection = ChatServerConnection.getSingleton();
        stopChatRoomAndRequestNewUsername();
    }

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(null,message);
    }

    public void displayMessageToALL(String fromUser, String messsage) {
        textAreaChat.append("\n" + fromUser + " > ALL > " + messsage);
    }

    public void displayPrivateMessage(String fromUser, String message) {
        textAreaChat.append("\n" + fromUser + " > Private > " + message);
    }

    public void updateListWtihNewUsers(String[] newTextAsArray) {
        System.out.println("...GUI is updating active user list with string array.");
        model = new DefaultListModel();
        for (int i = 1; i < newTextAsArray.length; i++) {
            System.out.println("...Listing active username: " + newTextAsArray[i]);
            model.addElement(newTextAsArray[i]);
        }
        listActiveUsers = new JList<>(model);
        listActiveUsers.updateUI();
        System.out.println("...GUI is done updating active user list.");
    }

    public void updateActiveUsersWithNew(String newUser) {
        model.addElement(newUser);
    }

    public void stopChatRoomAndRequestNewUsername() {
        String newUsername = "LOGIN#" + JOptionPane.showInputDialog("What you like to call yourself?");
        System.out.println("...User typed in his new username as: " + newUsername);
        chatServerConnection.sentTextToServer(newUsername);
    }

    public void removeUserFromActiveUserList(String userToRemove) {
        model.removeElement(userToRemove);
    }
}
