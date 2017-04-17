package service;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public abstract class ChatProtocolService {

    public abstract ServerCommand analyseServerLine(String newLine);

    public abstract ClientCommand analyseClientLine(String newLine);

    enum ServerCommand {
        ERROR(0,"[ERROR#[ERRORMESSAGE]")
        ,SEND_MESSAGE_TO_FORUM(1,"MSG#[FROMUSERNAME]#[MESSAGE]")
        ,SEND_MESSAGE_TO_ONE_USER(2,"MSG#[FROMUSERNAME]#[MESSAGE] ")
        ,LOGIN_USER_TO_FORUM(3,"LOGIN#[NEWUSERNAME]")
        ;

        public final int id;
        public final String code;

        ServerCommand(int id, String code) {
            this.id = id;
            this.code = code;
        }
    }

    enum ClientCommand {
        ERROR(0,"[ERROR#[ERRORMESSAGE]")
        ,SEND_MESSAGE_TO_ALL(1,"MSG#[ALL]#[MESSAGE]")
        ,SEND_MESSAGE_TO_SPECIFIC_USER(2,"MSG#[TOUSERNAME]#[MESSAGE]")
        ,ADD_MESSAGE(3,"MSG#[FROMUSERNAME]#[MESSAGE]")
        ,ADD_PRIVATE_MESSAGE(4,"MSG#[FROMUSERNAME]#[MESSAGE] ")
        ,OK_ON_LOGIN_AND_OPEN_LIST_OF_ACTIVE_USERS(5,"OK#[ACTIVEUSERNAME1]#[ACTIVEUSERNAME2]")
        ,UPDATE_LIST_OF_NEW_ACTIVE_USER(6,"UPDATE#[NEWUSERNAME]")
        ,INFORM_ATTEMPTED_USERNAME_ON_LOGIN_WAS_ALREADY_USED(7,"FAIL")
        ,REMOVE_USER_FROM_ACTIVE_USERS(8,"REMOVE#[USERNAME]")
        ;

        public final int id;
        public final String code;

        ClientCommand(int id, String code) {
            this.id = id;
            this.code = code;
        }
    }
}