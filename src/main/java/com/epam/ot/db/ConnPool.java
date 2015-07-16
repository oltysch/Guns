package com.epam.ot.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnPool {
    private static ConnPool ourInstance = new ConnPool();

    Connection connection;

    public static ConnPool getInstance() {
        return ourInstance;
    }

    private ConnPool() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sc", "");
        } catch (ClassNotFoundException e) {
            throw new ConnPoolEx("", e);
        } catch (SQLException e) {

        }
    }

    public Connection getConnection() {
        return connection;
    }
}
