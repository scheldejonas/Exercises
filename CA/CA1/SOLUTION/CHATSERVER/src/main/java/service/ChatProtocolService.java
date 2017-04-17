package service;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public abstract class ChatProtocolService {

    public abstract ServerCommand analyseServerLine(String newLine);

    public abstract ClientCommand analyseClientLine(String newLine);

    enum ServerCommand {
        SEND_MESSAGE_TO_FORUM(1)
        ,SEND_MESSAGE_TO_ONE_USER(2)
        ,LOGIN_USER_TO_FORUM(3)
        ;

        private final int id;

        ServerCommand(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    enum ClientCommand {
        ADD_MESSAGE(1)
        ,ADD_PRIVATE_MESSAGE(2)
        ,UPDATE_LIST_OF_ACTIVE_USERS(3)
        ,INFORM_ATTEMPTED_USERNAME_ON_LOGIN_WAS_ALREADY_USED(4)
        ,REMOVE_USER_FROM_ACTIVE_USERS(5)
        ;

        private final int id;

        ClientCommand(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}