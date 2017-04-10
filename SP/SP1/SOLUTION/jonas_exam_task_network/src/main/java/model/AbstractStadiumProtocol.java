package model;

/**
 * Created by scheldejonas on 10/04/2017.
 */
abstract public class AbstractStadiumProtocol {

    public Object getUnitObject(String connectionString) {

        if (connectionString.equals( new Monitor().getTYPE_ID() )) {
            return new Monitor();
        }
        if (connectionString.equals( new Turnstile().getTYPE_ID() )) {
            return new Turnstile();
        }
        return null;
    }

    public Commands getMessageReturnCommand(String recievedLine) {
        if (recievedLine.equals("AP")) {
            return Commands.ADD_PERSON;
        }
        return Commands.DO_NOTHING;
    }

    class Monitor {
        public Long id;
        public String name;

        public Monitor() {
        }

        public Monitor(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public Long getId() {
            return this.id;
        }

        public String getTYPE_ID() {
            return "UNIT_MONITOR";
        }

        @Override
        public String toString() {
            return "Monitor: {" +
                    "id=" + id +
                    ",name=" + name +
                    ",type=" + getTYPE_ID() +
                    "}";
        }
    }

    class Turnstile {
        public Long id;
        public String name;

        public Turnstile() {
        }

        public Turnstile(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public Long getId() {
            return this.id;
        }

        public String getTYPE_ID() {
            return "UNIT_TURNSTILE";
        }

        @Override
        public String toString() {
            return "Turnstile: {" +
                    "id=" + id +
                    ",name=" + name +
                    ",type=" + getTYPE_ID() +
                    "}";
        }
    }

    enum Commands {
        ADD_PERSON
        , DO_NOTHING;

        Commands() {
        }
    }
}
