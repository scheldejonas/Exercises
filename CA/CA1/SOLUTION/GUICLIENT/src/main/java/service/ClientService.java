package service;

import controller.GuiController;
import service.ChatProtocolService.ClientCommand;

import java.util.concurrent.locks.ReentrantLock;

import static service.ChatProtocolService.ClientCommand.*;

/**
 * Created by schelde on 18/04/17.
 */
public class ClientService {
    private static final ClientService singleton = new ClientService();
    private ReentrantLock lock = null;

    // Autowired objects
    private ChatProtocolServiceImpl chatProtocolService = ChatProtocolServiceImpl.getSingleton();
    private GuiController guiController = GuiController.getSingleton();

    private ClientService() {
    }

    public static ClientService getSingleton() {
        return singleton;
    }

    public void setLock(ReentrantLock lock) {
        this.lock = lock;
    }

    public void newTextFromServer(String newReceivedText) {
        try {
            lock.lock();
            ClientCommand clientCommand = chatProtocolService.analyseClientLine(newReceivedText);
            String[] newTextAsArray = newReceivedText.split("#");
            lock.unlock();
            if (clientCommand.id == ADD_MESSAGE.id) {
                guiController.displayMessageToALL(newTextAsArray[1], newTextAsArray[2]);
            }
            if (clientCommand.id == ADD_PRIVATE_MESSAGE.id) {
                guiController.displayPrivateMessage(newTextAsArray[1], newTextAsArray[2]);
            }
            if (clientCommand.id == OK_ON_LOGIN_AND_OPEN_LIST_OF_ACTIVE_USERS.id) {
                guiController.updateListWithNewUsers(newTextAsArray);
            }
            if (clientCommand.id == UPDATE_LIST_OF_NEW_ACTIVE_USER.id) {
                guiController.updateActiveUsersWithNew(newTextAsArray[1]);
            }
            if (clientCommand.id == INFORM_ATTEMPTED_USERNAME_ON_LOGIN_WAS_ALREADY_USED.id) {
                guiController.stopChatRoomAndRequestNewUsername();
            }
            if (clientCommand.id == REMOVE_USER_FROM_ACTIVE_USERS.id) {
                guiController.removeUserFromActiveUserList(newTextAsArray[1]);
            }
            if (clientCommand.id == ERROR.id) {
                guiController.displayErrorMessage(newTextAsArray[1]);
            }
        } catch (Throwable error) {
            lock.unlock();
            System.out.println("...There was a problem while handleing new text from server.");
            error.printStackTrace();
        }
    }
}
