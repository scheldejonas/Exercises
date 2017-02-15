package controller;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public class LockMissingException extends Throwable {
    public LockMissingException() {
        super("Reentrant Lock for making sure Synchron threads activity is done correct, not set or passed during construction");
    }
}
