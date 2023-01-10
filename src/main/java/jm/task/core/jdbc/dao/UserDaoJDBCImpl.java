package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String tableName = User.class.getSimpleName();
    private static final Field[] userFields = User.class.getDeclaredFields();// .class.getFields();
    //private static final String idField = User.class.getFields()[0].getName();
    private static final String idField = userFields[0].getName();
    private static final String nameField = userFields[1].getName();
    private static final String lastnameField = userFields[2].getName();
    private static final String ageField = userFields[3].getName();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
/*        String str = "CREATE TABLE IF NOT EXISTS ? (" +
                "? BIGINT primary key auto_increment, ? VARCHAR(10), ? VARCHAR(10), ? TINYINT)";*/
        String str = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (" +
                idField + " BIGINT primary key auto_increment, " +
                nameField + " VARCHAR(10), " +
                lastnameField + " VARCHAR(10), " +
                ageField + " TINYINT) ENGINE=InnoDB   DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT";

        try(Connection conn = Util.getConnection();
                PreparedStatement statement = conn.prepareStatement(str)) {
/*            statement.setString(1, tableName);
            statement.setString(2, idField);
            statement.setString(3, nameField);
            statement.setString(4, lastnameField);
            statement.setString(5, ageField);*/
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        //String str = "DROP TABLE IF EXISTS ?";
        String str = "DROP TABLE IF EXISTS " + tableName;

        try(Connection conn = Util.getConnection();
                PreparedStatement statement = conn.prepareStatement(str)) {
            //statement.setString(1, tableName);
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        //String str = "INSERT INTO ? (?,?,?) VALUES (?,?,?)";
        String str = "INSERT INTO " + tableName +
                " (" + nameField + "," + lastnameField + "," + ageField + ") " +
                "VALUES (?,?,?)";

        try(Connection conn = Util.getConnection();
                PreparedStatement statement = conn.prepareStatement(str)) {
/*            statement.setString(1, tableName);
            statement.setString(2, nameField);
            statement.setString(3, lastnameField);
            statement.setString(4, ageField);
            statement.setString(5, name);
            statement.setString(6, lastName);
            statement.setByte(7, age);*/
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
/*            String str = "INSERT INTO " + tableName +
                    " (" + nameField + "," + lastnameField + "," + ageField + ") " +
                    "VALUES ('" + name + "','" + lastName + "'," + age + ")";*/
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        //String str = "DELETE FROM ? WHERE ?.?=?";
        String str = "DELETE FROM " + tableName +
                " WHERE " + tableName + "." + idField + "=?";

        try(Connection conn = Util.getConnection();
                PreparedStatement statement = conn.prepareStatement(str)) {
            /*statement.setString(1, tableName);
            statement.setString(2, tableName);
            statement.setString(3, idField);*/
            statement.setLong(1, id);
            statement.executeUpdate();
            //statement.executeUpdate("DELETE FROM " + tableName + " WHERE " + tableName + "." + idField + "=" + id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try(Connection conn = Util.getConnection();
                Statement statement = conn.createStatement();
                //PreparedStatement statement = conn.prepareStatement("SELECT * FROM " + tableName);
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getString(2),
                        resultSet.getString(3),
                        Byte.parseByte(resultSet.getString(4))));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String str = "TRUNCATE TABLE " + tableName;
        try(Connection conn = Util.getConnection();
                PreparedStatement statement = conn.prepareStatement(str)) {

            //statement.setString(1, tableName);
            statement.executeUpdate();
            //statement.executeUpdate("DELETE FROM " + tableName);
            //statement.executeUpdate("TRUNCATE TABLE " + tableName);
            //statement.execute("DELETE FROM " + tableName);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
