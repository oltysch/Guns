package com.epam.ot.db;

import com.epam.ot.util.PropertyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool {
    private static ConnectionPool instance;
    private PropertyManager propertyManager = new PropertyManager("connection.properties");
    private Vector<Connection> availableConnections = new Vector<Connection>();
    private Vector<Connection> usedConnections = new Vector<Connection>();

    public ConnectionPool() {

    }

    public ConnectionPool(int initConnectionCount) {
        try {
            Class.forName(propertyManager.getProperty("driver"));
        } catch (Exception e) {
            throw new ConnectionPoolException(e);
        }
        for (int i = 0; i < initConnectionCount; i++) {
            availableConnections.addElement(newConnection());
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection newConn = null;
        if (availableConnections.size() == 0) {
            newConn = newConnection();
        } else {
            newConn = (Connection) availableConnections.lastElement();
            availableConnections.removeElement(newConn);
        }
        usedConnections.addElement(newConn);
        return newConn;
    }

    public synchronized void putBack(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConnections.removeElement(c)) {
                availableConnections.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConnections");
            }
        }
    }

    private Connection newConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(propertyManager.getProperty("url"), propertyManager.getProperty("login"), propertyManager.getProperty("password"));
        } catch (Exception e) {
            throw new ConnectionPoolException(e);
        }
        return conn;
    }

    public int getAvailableConnectionsCount() {
        return availableConnections.size();
    }
}
