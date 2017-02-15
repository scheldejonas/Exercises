package service;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public interface ChatProtocol {

    /**
     * This method purpose is to seperate the input stream text and fire it towards the meant command and to deliver
     * the final Response string
     * @param recievedText
     * @return
     */
    String handleRecievedLine(String recievedText);

    /**
     * Purpopse is to adjust data and prepare the response String for the Client
     * @param inputMessage
     * @return
     */
    String commandLogIn(String inputMessage);

    /**
     * Purpopse is to adjust data and prepare the response String for the Client
     * @return
     */
    String commandLogOut();

    /**
     * Purpopse is to adjust data and prepare the response String for the Client
     * @param message
     * @param userName
     * @return
     */
    String commandMessage(String message, String userName);

}
