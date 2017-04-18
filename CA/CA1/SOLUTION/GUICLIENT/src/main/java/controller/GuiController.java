package controller;

import view.SwingClientForm;

/**
 * Created by schelde on 18/04/17.
 */
public class GuiController {
    private static final GuiController singleton = new GuiController();

    private SwingClientForm swingClientForm = SwingClientForm.getSingleton();

    private GuiController() {
    }

    public static GuiController getSingleton() {
        return singleton;
    }


    public void displayErrorMessage(String message) {
        swingClientForm.displayErrorMessage(message);
    }

    public void displayMessageToALL(String fromUser, String messsage) {
        swingClientForm.displayMessageToALL(fromUser, messsage);
    }

    public void displayPrivateMessage(String fromUser, String message) {
        swingClientForm.displayPrivateMessage(fromUser,message);
    }

    public void updateListWithNewUsers(String[] newTextAsArray) {
        swingClientForm.updateListWtihNewUsers(newTextAsArray);
    }

    public void updateActiveUsersWithNew(String newUser) {
        swingClientForm.updateActiveUsersWithNew(newUser);
    }

    public void stopChatRoomAndRequestNewUsername() {
        swingClientForm.stopChatRoomAndRequestNewUsername();
    }

    public void removeUserFromActiveUserList(String userToRemove) {
        swingClientForm.removeUserFromActiveUserList(userToRemove);
    }
}
