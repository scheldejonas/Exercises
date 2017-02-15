package dao;

/**
 * Created by scheldejonas on 15/02/2017.
 */
public enum Profiles {
    SCHELDE_LOCAL("localhost","root","tEb6M>CAEuua1","chatone"),
    LIVE_READER("localhost","reader","6desidd6Fi7f29Q94XQF","chatone")
    ;

    private final String host;
    private final String userName;
    private final String passWord;
    private final String schemaName;

    Profiles(String host, String userName, String passWord, String schemaName) {
        this.host = host;
        this.userName = userName;
        this.passWord = passWord;
        this.schemaName = schemaName;
    }

    public String getHost() {
        return host;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getSchemaName() {
        return schemaName;
    }

    @Override
    public String toString() {
        return String.format("Profile %s - Host: %s - Schema: %s - UserName: %s - PassWord: %s \n", name(),this.host, this.schemaName, this.userName, this.passWord);
    }
}
