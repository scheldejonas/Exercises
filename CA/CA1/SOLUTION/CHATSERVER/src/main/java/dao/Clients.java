package dao;

import domain.Client;
import domain.ClientConnection;
import domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by scheldejonas on 17/04/2017.
 */
public class Clients {
    private static final Clients singleton = new Clients();
    private List<ClientConnection> activeClients = new ArrayList<>();
    private ThreadPoolExecutor connectionPool = null;
    private ReentrantLock reentrantLock = new ReentrantLock(true);

    private Clients() {
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        this.connectionPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(50, threadFactory);
    }

    public static Clients getSingleton() {
        return singleton;
    }

    public List<ClientConnection> getActiveClients() {
        return activeClients;
    }

    public ReentrantLock getReentrantLock() {
        return this.reentrantLock;
    }

    public ThreadPoolExecutor getConnectionPool() {
        return this.connectionPool;
    }
}
