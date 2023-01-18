package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//import java.lang.module.Configuration;
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

    public static SessionFactory getSessionFactory() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.connection.url", dbUrl);
        prop.setProperty("dialect", "org.hibernate.dialect.MySQL");
        prop.setProperty("hibernate.connection.CharSet", "utf8");
        prop.setProperty("hibernate.connection.characterEncoding", "utf8");
        prop.setProperty("hibernate.connection.useUnicode", "true");

        prop.setProperty("hibernate.connection.username", user);
        prop.setProperty("hibernate.connection.password", pass);
        //prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

        return new Configuration().addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
    }
}
