package com.epam.ot.dao;

import com.epam.ot.db.ConnPool;
import com.epam.ot.entity.Gun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcGunDao implements GunDao {
    /*ConnPool pool;
    public Gun findById(long id) {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, firstName, lastName FROM gun WHERE id = ?");
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean found = resultSet.next();
        if (found) {
            Gun gun = new Gun();
            gun.setModel(resultSet.getString("model"));
            gun.setMaterial(resultSet.getString("material"));
        }
    }

    public void update() {

    }

    public Gun save() {
        return null;
    }

    public Gun merge() {
        return null;
    }

    public Gun insert() {
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSON (id, firstName, lastName) VALUES (DEFAULT, ?, ?)");
        preparedStatement.setString(1, gun.getModel());
        preparedStatement.setString(2, gun.getFiringRange());
        preparedStatement.executeUpdate();
        generatedKeys.next();
        long id = generatedKeys.getLong(1);
        person.setId(id);
        return null;
    }

    public void upset() {

    }*/
}
