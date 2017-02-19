import view.SwingClientConnectForm;

import javax.swing.*;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        String host = "10.50.137.177";
        String host2 = "10.50.130.116";
        String localhost = "localhost";
        int normalPort = 8081;
        startSwingGui();
    }

    private static void startSwingGui() {
        JFrame jFrame = new JFrame("SwingClientConnectForm");
        SwingClientConnectForm swingClientConnectForm = new SwingClientConnectForm(jFrame);
        jFrame.setContentPane(swingClientConnectForm.getjPanelClientConnectForm());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
