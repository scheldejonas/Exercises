package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class User {
    private String name = null;
    private LocalDateTime joinedDateTime = LocalDateTime.now();
    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> seenMessages = new ArrayList<>();

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getJoinedDateTime() {
        return joinedDateTime;
    }

    public void setJoinedDateTime(LocalDateTime joinedDateTime) {
        this.joinedDateTime = joinedDateTime;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public List<Message> getSeenMessages() {
        return seenMessages;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", joinedDateTime=" + joinedDateTime +
                ", sentMessages=" + sentMessages.size() +
                ", seenMessages=" + seenMessages.size() +
                '}';
    }
}
