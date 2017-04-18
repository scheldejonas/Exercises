package service;

/**
 * Created by schelde on 18/04/17.
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
        return null;
    }

    @Override
    public ClientCommand analyseClientLine(String newLine) {
        String[] newTextLineAsArray = newLine.split("#");
        System.out.println("...Starting to analyse new received textline from server on client in protocol: " + newLine);
        if (newTextLineAsArray[0].equals("MSG")) {
            if (newTextLineAsArray[2].substring(newTextLineAsArray[2].length()-1,newTextLineAsArray[2].length()).equals(" ")) {
                return ClientCommand.ADD_PRIVATE_MESSAGE;
            } else {
                return ClientCommand.ADD_MESSAGE;
            }
        }
        if (newTextLineAsArray[0].equals("FAIL")) {
            return ClientCommand.INFORM_ATTEMPTED_USERNAME_ON_LOGIN_WAS_ALREADY_USED;
        }
        if (newTextLineAsArray[0].equals("OK")) {
            return ClientCommand.OK_ON_LOGIN_AND_OPEN_LIST_OF_ACTIVE_USERS;
        }
        if (newTextLineAsArray[0].equals("UPDATE")) {
            return ClientCommand.UPDATE_LIST_OF_NEW_ACTIVE_USER;
        }
        if (newTextLineAsArray[0].equals("REMOVE")) {
            return ClientCommand.REMOVE_USER_FROM_ACTIVE_USERS;
        }
        if (newTextLineAsArray[0].equals("ERROR")) {
            return ClientCommand.ERROR;
        }
        return ClientCommand.ERROR;
    }
}
