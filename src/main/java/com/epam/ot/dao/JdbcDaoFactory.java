package com.epam.ot.dao;

public class JdbcDaoFactory extends DaoFactory {
    @Override
    public GunDao newGunDao() {
        return new JdbcGunDao();
    }
}
