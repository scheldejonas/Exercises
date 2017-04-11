package model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by scheldejonas on 10/04/2017.
 */
public class Database {
    private static final Database singleton = new Database();
    private AtomicLong peopleEnteredCount = new AtomicLong(0);
    private Map<Long, AtomicLong> units = new HashMap<>();
    private StadiumProtocol stadiumProtocol = StadiumProtocol.getSingleton();

    private Database() {
    }

    public static Database getSingleton() {
        return singleton;
    }

    public long addOnePerson(Long unitId) {
        long totalPeople = peopleEnteredCount.addAndGet(1);
        System.out.println("...Database has incremented with " + totalPeople);
        units.put(unitId, new AtomicLong(units.get(unitId).incrementAndGet()) );
        return totalPeople;
    }

    public long getPeople() {
        return peopleEnteredCount.get();
    }

    public Map<Long, AtomicLong> getUnits() {
        return units;
    }
}
