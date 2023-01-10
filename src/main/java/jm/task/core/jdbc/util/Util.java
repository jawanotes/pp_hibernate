package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    //private static String user = "adminer";
    private static final String user = "debian-sys-maint";
    //private static String pass = "adminerpass";
    private static final String pass = "S9cuWHbN5w8SCIDL";
    //private String db = "pp113";
    //private static String dbUrl = "jdbc:mysql://192.168.88.110:3306/pp113";
    private static final String dbUrl = "jdbc:mysql://localhost:3306/pp113";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.Driver");
        //Class.forName("org.mariadb.jdbc");
        Properties prop = new Properties();
        prop.put("user", user);
        prop.put("password", pass);
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        //return DriverManager.getConnection(dbUrl, user, pass);
        return DriverManager.getConnection(dbUrl, prop);
    }
}
