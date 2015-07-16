package com.epam.ot.dao;

public abstract class DaoFactory {
    public static DaoFactory getInstance() {
        return new JdbcDaoFactory();
    }

    public GunDao newGunDao() {
        return null;
    }
}
