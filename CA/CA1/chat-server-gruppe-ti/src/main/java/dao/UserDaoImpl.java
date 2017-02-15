package dao;

import domain.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is for contacting the database and making sure about how many users is online and whose is not.
 *
 * Created by scheldejonas on 15/02/2017.
 */
public class UserDaoImpl implements UserDao {

    private static final UserDaoImpl singleton = new UserDaoImpl();

    private String profile = "SCHELDE_LOCAL";

    // Making private constructor, to prevent others from contructing more then one instance of this class
    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return singleton;
    }

    @Override
    public List<User> getAllUsers() {

        String sqlStatement = "SELECT * FROM chatone.User";

        List<User> userList = new ArrayList<>();

        try (Connection connection = DatabaseConnectionConfig.getConnection(profile);
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sqlStatement);

            userList = makeResulSetToList(resultSet);

        } catch (ConnectionProfileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public List<User> getActiveUsers() {

        String sqlStatement = "SELECT * FROM Invoice WHERE isActive = TRUE";

        List<User> activeUserList = new ArrayList<>();

        try (Connection connection = DatabaseConnectionConfig.getConnection(Profiles.SCHELDE_LOCAL.name());
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sqlStatement);

            activeUserList = makeResulSetToList(resultSet);

        } catch (SQLException | ClassNotFoundException | ConnectionProfileNotFoundException exception) {
            System.out.printf("In %s, couldn't do: %s \n", this.getClass().getName(), exception.getMessage());
            exception.printStackTrace();
        }

        return activeUserList;

    }

    private List<User> makeResulSetToList(ResultSet resultSet) throws SQLException {

        List<User> userList = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUserName(resultSet.getString("userName"));
            user.setPassword(resultSet.getString("passWord"));
            user.setActive(resultSet.getBoolean("isActive"));
            userList.add(user);
        }

        return userList;

    }

    @Override
    public void updateActive(User user) {

        String sqlStatement = String.format("UPDATE chatone.User " +
                "SET isActive = %s" +
                "WHERE id = %s", user.isActive(), user.getId()
        );

        try (Connection connection = DatabaseConnectionConfig.getConnection(profile);
             Statement statement = connection.createStatement()) {

            statement.executeQuery(sqlStatement);

        } catch (ConnectionProfileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}