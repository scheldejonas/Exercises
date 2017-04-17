package domain;

import java.time.LocalDateTime;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class Message {
    private final String toUser;
    private final String fromUser;
    private final String message;
    private final LocalDateTime sentDateTime = LocalDateTime.now();

    public Message(String fromUserName, String toUserName, String message) {
        this.fromUser = fromUserName;
        this.toUser = toUserName;
        this.message = message;
    }

    public String getToUser() {
        return toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "toUser='" + toUser + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", message='" + message + '\'' +
                ", sentDateTime=" + sentDateTime +
                '}';
    }
}
