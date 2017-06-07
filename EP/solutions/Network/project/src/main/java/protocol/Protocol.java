package protocol;

/**
 * Created by schelde on 07/06/17.
 */
public enum Protocol {
    INCREMENT_PERSON("INC"),
    SET_ID("SETID"),
    GET_ID("GETID");

    private String command;

    Protocol(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static String getSeparator() {
        return "_";
    }

    // UNITTYPEID_COMMAND_VALUES_HISTORY
    public static String formatSetId(int id, String unitType) {
        unitType = unitType.toUpperCase();
        return unitType + getSeparator() + SET_ID.command + getSeparator() + id;
    }

    public static String formatIncrement(String unitType, int id) {
        unitType = unitType.toUpperCase() + id;
        return unitType + getSeparator() + INCREMENT_PERSON.command;
    }

    public static String formatGetId(String unitType) {
        unitType = unitType.toUpperCase();
        return unitType + getSeparator() + GET_ID.command;
    }

    public static Protocol findCommandProtocol(String textLine) {
        for (Protocol protocol : Protocol.values()) {
            if (protocol.command.equals(textLine)) {
                return protocol;
            }
        }
        return null;
    }
}
