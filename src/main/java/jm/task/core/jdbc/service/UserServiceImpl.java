package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;

/*import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;*/
import java.util.List;

/**
 * Комментарии оставлены в целях археологии
 * */
public class UserServiceImpl implements UserService {
    //private User user = new User();
/*    private static final String tableName = User.class.getSimpleName();
    private static final Field[] userFields = User.class.getDeclaredFields();// .class.getFields();
    //private static final String idField = User.class.getFields()[0].getName();
    private static final String idField = userFields[0].getName();
    private static final String nameField = userFields[1].getName();
    private static final String lastnameField = userFields[2].getName();
    private static final String ageField = userFields[3].getName();*/

    private final UserDao userDao = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
/*        try(Connection conn = Util.getConnection();
                Statement statement = conn.createStatement()) {
            String str = "CREATE TABLE IF NOT EXISTS " + tableName +
                    " (" +
                    idField + " BIGINT primary key auto_increment, " +
                    nameField + " VARCHAR(10), " +
                    lastnameField + " VARCHAR(10), " +
                    ageField + " TINYINT)";
            statement.executeUpdate(str);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
/*        try(Connection conn = Util.getConnection();
                Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
/*        try(Connection conn = Util.getConnection();
                Statement statement = conn.createStatement()) {
            String str = "INSERT INTO " + tableName +
                    " (" + nameField + "," + lastnameField + "," + ageField + ") " +
                    "VALUES (\'" + name + "\',\'" + lastName + "\'," + age + ")";
            statement.executeUpdate(str);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
/*        try(Connection conn = Util.getConnection();
                Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM " + tableName + " WHERE " + tableName + "." + idField + "=" + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
/*        List<User> userList = new ArrayList<>();

        try(Connection conn = Util.getConnection();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Byte.parseByte(resultSet.getString(4))));
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userList;*/
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
/*        try(Connection conn = Util.getConnection();
                Statement statement = conn.createStatement()) {

            //statement.executeUpdate("DELETE FROM " + tableName);
            statement.executeUpdate("TRUNCATE TABLE " + tableName);
            //statement.execute("DELETE FROM " + tableName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }
}
