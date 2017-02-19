package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class SwingClientConnectForm {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel jLabelUsername;
    private JButton loginButton;
    private JPanel jPanelClientConnectForm;

    public SwingClientConnectForm(JFrame jFrame) {
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JTextField getTextField2() {
        return textField2;
    }

    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    public JTextField getTextField3() {
        return textField3;
    }

    public void setTextField3(JTextField textField3) {
        this.textField3 = textField3;
    }

    public JLabel getjLabelUsername() {
        return jLabelUsername;
    }

    public void setjLabelUsername(JLabel jLabelUsername) {
        this.jLabelUsername = jLabelUsername;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(JButton loginButton) {
        this.loginButton = loginButton;
    }

    public JPanel getjPanelClientConnectForm() {
        return jPanelClientConnectForm;
    }

    public void setjPanelClientConnectForm(JPanel jPanelClientConnectForm) {
        this.jPanelClientConnectForm = jPanelClientConnectForm;
    }
}
