package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
//import jm.task.core.jdbc.util.Util;


/*import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Ivan", "Ivanov", (byte) 11);
        userService.saveUser("Petr", "Petrov", (byte) 22);
        userService.saveUser("Sidor", "Sidorov", (byte) 33);
        userService.saveUser("Незнайка", "Незнанский", (byte) 44);// Have "?" with cyrillic

        List<User> userList = userService.getAllUsers();
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user.getId()).append(" ")
                    .append(user.getName()).append(" ")
                    .append(user.getLastName()).append(" ")
                    .append(user.getAge()).append("\n");
            //System.out.println(user.getId() + user.getName() + user.getLastName() + user.getAge());
        }
        System.out.println(sb);

        userService.dropUsersTable();

/*        try (Connection myConn = Util.getConnection();
                Statement statement = myConn.createStatement()) {
            //CREATE TABLE IF NOT EXISTS users (id BIGINT primary key auto_increment, name VARCHAR(10), lastname VARCHAR(10), age TINYINT)


            //statement.executeUpdate("CREATE TABLE User (firstname VARCHAR(12), secondname VARCHAR(12))");
            //statement.executeUpdate("INSERT INTO User ('Ivan', 'Ivanov')");
            statement.executeUpdate("INSERT INTO User (name, lastName) VALUES ('Ivan', 'Ivanov')");
            //statement.executeUpdate("INSERT INTO User ('Petr', 'Petrov')");
            statement.executeUpdate("INSERT INTO User (name, lastName) VALUES ('Petr', 'Petrov')");
            //statement.executeUpdate("INSERT INTO User ('Sidor', 'Sidorov')");
            statement.executeUpdate("INSERT INTO User (name, lastName) VALUES ('Sidor', 'Sidorov')");
            //statement.executeUpdate("INSERT INTO User ('Незнайка', 'Незнанский')");
            statement.executeUpdate("INSERT INTO User (name, lastName) VALUES ('Незнайка', 'Незнанский')");
            //statement.executeUpdate("INSERT INTO User (firstname, secondname) VALUES ('Ivan', 'Ivanov')");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM User");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
            }
            statement.executeUpdate("DROP TABLE User");
            //statement.

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }
}
