package com.epam.ot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {
    //TODO make connectionPool
    private static ConnectionPool ourInstance = new ConnectionPool();
    public static String DRIVER = "org.h2.Driver";
    public static String CONNECT = "jdbc:h2:~/test";
    public static String CONNECT_ID = "sc";
    public static String PASSWORD = "";

    Connection connection;

    public static ConnectionPool getInstance() {
        return ourInstance;
    }

    private ConnectionPool() {
        try {
            Class.forName("org.h2.Driver");

            connection = DriverManager.getConnection("jdbc:h2:~/test", "sc", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }


}
