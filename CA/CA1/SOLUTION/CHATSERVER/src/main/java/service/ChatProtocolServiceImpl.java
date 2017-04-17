package service;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class ChatProtocolServiceImpl extends ChatProtocolService {

    private static final ChatProtocolServiceImpl singleton = new ChatProtocolServiceImpl();

    private ChatProtocolServiceImpl() {
    }

    public static ChatProtocolServiceImpl getSingleton() {
        return singleton;
    }

    @Override
    public ServerCommand analyseServerLine(String newLine) {
        String[] newTextLineInArray = newLine.split("#");
        System.out.println("...Starting to analyse new received textline from client on server: " + newLine);
        if (newTextLineInArray[0].equals("LOGIN")) {
            return ServerCommand.LOGIN_USER_TO_FORUM;
        }
        if (newTextLineInArray[0].equals("MSG")) {
            if (newTextLineInArray[1].equals("ALL")) {
                return ServerCommand.SEND_MESSAGE_TO_FORUM;
            }
            if (!newTextLineInArray[1].equals("ALL")) {
                return ServerCommand.SEND_MESSAGE_TO_ONE_USER;
            }
        }
        return null;
    }

    @Override
    public ClientCommand analyseClientLine(String newLine) {
        return null;
    }

}
