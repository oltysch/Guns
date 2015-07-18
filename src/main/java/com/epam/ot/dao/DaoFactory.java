package com.epam.ot.dao;

import com.epam.ot.db.ConnectionPool;

import java.sql.Connection;

public abstract class DaoFactory {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    public static DaoFactory getInstance() {
        Connection connection = pool.getConnection();
        return new JdbcDaoFactory(connection);
    }

    public abstract GunDao createGunDao();

    public abstract void beginConnectionScope();

    public abstract void beginTransaction();

    public abstract void endTransaction();

    public abstract void endConnectionScope();
}
