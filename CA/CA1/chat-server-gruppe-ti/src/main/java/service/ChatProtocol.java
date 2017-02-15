package service;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public interface ChatProtocol {

    String commandLogIn(String inputMessage);

    String commandLogOut();

    String commandMessage();

    String handleRecievedLine(String recievedText);
}
