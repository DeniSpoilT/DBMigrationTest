package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class MySQLConnectionManager {
    public static final String URL_KEY = "mysql.url";
    public static final String LOGIN_KEY = "mysql.username";
    public static final String PASSWORD_KEY = "mysql.password";
    static Connection connection;

    public MySQLConnectionManager() {
        loadDriver();
        openConnection();
    }

    private static void loadDriver() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
    }

    private static void openConnection(){
        try {
            connection =  DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(LOGIN_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public Connection getConnection(){
       return connection;
    }

}
