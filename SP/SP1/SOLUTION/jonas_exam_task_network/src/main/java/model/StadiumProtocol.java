package model;

import javax.xml.crypto.Data;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by scheldejonas on 10/04/2017.
 */
public class StadiumProtocol {
    private final static StadiumProtocol singleton = new StadiumProtocol();
    private Database database = Database.getSingleton();

    private StadiumProtocol() {
    }

    public static StadiumProtocol getSingleton() {
        return singleton;
    }

    public Unit getUnitObject(String connectionString) {
        if (connectionString.equals( "UNIT_" + Monitor.TYPE_ID )) {
            Monitor monitor = new Monitor(new Long(database.getUnits().size()), "Monitor");
            //database.getUnits().put(monitor.getId(), new AtomicLong(0));
            System.out.println("...New Monitor Unit created and saved for connection actions.");
            return monitor;
        }
        if (connectionString.equals( "UNIT_" + Turnstile.TYPE_ID )) {
            Turnstile turnstile = new Turnstile(new Long(database.getUnits().size()) , "Turnstile");
            database.getUnits().put(turnstile.getId(), new AtomicLong(0));
            System.out.println("...New Turnstile Unit created and saved for connection actions.");
            return turnstile;
        }
        System.out.println("...Object type didn't got identified, when new unit was obtained in Stadium protocol.");
        return null;
    }

    public Commands getMessageReturnCommand(String recievedLine) {
        if (recievedLine.equals("AP")) {
            return Commands.ADD_PERSON;
        }
        if (recievedLine.equals("EPC")) {
            return Commands.ECHO_TOTAL_PEOPLE_COUNT;
        }
        if (recievedLine.equals("EAUWC")) {
            return Commands.ECHO_ALL_UNITS_WITH_COUNT;
        }
        return Commands.DO_NOTHING;
    }

    public class Unit {
        public Long id;
        public String name;

        public Unit() {
        }

        public Unit(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public Long getId() {
            return this.id;
        }

        @Override
        public String toString() {
            String type = "NOTHING";
            if (this instanceof Monitor) {
                type = "MONITOR";
            }
            if (this instanceof Turnstile) {
                type = "TURNSTILE";
            }
            return "Monitor: {" +
                    "id=" + id +
                    ",name=" + name +
                    ",type=" +  type +
                    "}";
        }
    }

    public class Monitor extends Unit {
        public static final String TYPE_ID = "MONITOR";
        public Monitor(Long id, String name) {
            super(id, name);
        }
    }

    public class Turnstile extends Unit {
        public static final String TYPE_ID = "TURNSTILE";
        public Turnstile(Long id, String name) {
            super(id, name);
        }
    }

    public enum Commands {
        ADD_PERSON
        , ECHO_TOTAL_PEOPLE_COUNT
        , ECHO_ALL_UNITS_WITH_COUNT
        , DO_NOTHING;

        Commands() {
        }
    }
}
