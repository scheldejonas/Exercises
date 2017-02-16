package controller;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public class ServerNotBoundExecption extends Throwable {
    public ServerNotBoundExecption() {
        super("Chat Server is not binded to a host and a port yet. Runnable is stopped");
    }
}
