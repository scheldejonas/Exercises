import view.SwingClientConnectForm;

/**
 * Created by schelde on 18/04/17.
 */
public class Application {
    public static void main(String[] args) {
        SwingClientConnectForm connectForm = SwingClientConnectForm.getSingleton();
        connectForm.openAndShowChatGUI();
    }
}
