package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by scheldejonas on 16/02/2017.
 */
public class ClientChat extends Component {
    private final JFrame jFrame;
    private final Application application;
    private JButton sendButton;
    private JTextField messageInput;
    private JTextArea chatArea;
    private JTextArea activeUserArea;
    private JPanel jPanelChat;

    public ClientChat(JFrame jFrame, final Application application) {
        this.jFrame = jFrame;
        this.application = application;
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                application.getPrinterToService().println(messageInput.getText());
                emptyOutMessageInput();
            }
        });
    }

    private void emptyOutMessageInput() {
        messageInput.setText("");
    }

    public void pointTheChatClientService() {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            application.setPathForServiceJar( fileChooser.getSelectedFile().getAbsolutePath() );
            application.setFile( fileChooser.getSelectedFile() );
        } else {
            System.out.println("File access cancelled by user.");
        }
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public Application getApplication() {
        return application;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JTextField getMessageInput() {
        return messageInput;
    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public JTextArea getActiveUserArea() {
        return activeUserArea;
    }

    public JPanel getjPanelChat() {
        return jPanelChat;
    }
}
