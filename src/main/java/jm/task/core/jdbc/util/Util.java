package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    //private static String user = "adminer";
    private static String user = "debian-sys-maint";
    //private static String pass = "adminerpass";
    private static String pass = "S9cuWHbN5w8SCIDL";
    //private String db = "pp113";
    //private static String dbUrl = "jdbc:mysql://192.168.88.110:3306/pp113";
    private static String dbUrl = "jdbc:mysql://localhost:3306/pp113";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //Class.forName("org.mariadb.jdbc");
        return DriverManager.getConnection(dbUrl, user, pass);
    }
}
