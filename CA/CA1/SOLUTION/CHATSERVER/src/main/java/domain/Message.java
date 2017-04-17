package domain;

import java.time.LocalDateTime;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class Message {
    private String message = null;
    private User from = null;
    private User to = null;
    private LocalDateTime sentDateTime = LocalDateTime.now();

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", sentDateTime=" + sentDateTime +
                '}';
    }
}
