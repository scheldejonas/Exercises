import view.ScannerChatUI;
import view.SwingClientConnectForm;

import javax.swing.*;

/**
 * Created by scheldejonas on 18/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        String localhost = "localhost";
        int normalPort = 8081;
        //startSwingGui();
        startScannerUI();
    }

    private static void startSwingGui() {
        JFrame jFrame = new JFrame("SwingClientConnectForm");
        SwingClientConnectForm swingClientConnectForm = new SwingClientConnectForm(jFrame);
        jFrame.setContentPane(swingClientConnectForm.getjPanelClientConnectForm());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private static void startScannerUI() {
        ScannerChatUI scannerChatUI = new ScannerChatUI();
    }
}
