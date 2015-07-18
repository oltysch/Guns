package com.epam.ot.dao;

import com.epam.ot.db.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcDaoFactory extends DaoFactory {
    private final Connection connection;

    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public GunDao createGunDao() {
        return new JdbcGunDao(connection);
    }

    @Override
    public void beginConnectionScope() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void endTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void endConnectionScope() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
