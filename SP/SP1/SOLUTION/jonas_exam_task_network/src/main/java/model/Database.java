package model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by scheldejonas on 10/04/2017.
 */
public class Database {
    private static final Database singleton = new Database();
    private AtomicLong peopleEnteredCount = new AtomicLong(0);

    public Database() {
    }

    public long addOnePerson() {
        return peopleEnteredCount.addAndGet(1);
    }

    public static Database getSingleton() {
        return singleton;
    }
}
