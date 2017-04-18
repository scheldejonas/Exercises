import view.ScannerChatUI;
/**
 * Created by scheldejonas on 18/02/2017.
 */
public class Application {
    public static void main(String[] args) {
        String localhost = "localhost";
        int normalPort = 8084;
        //startSwingGui();
        startScannerUI();
    }

    private static void startScannerUI() {
        ScannerChatUI scannerChatUI = new ScannerChatUI();
    }
}
