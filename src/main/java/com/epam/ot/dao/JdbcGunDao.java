package com.epam.ot.dao;

import com.epam.ot.entity.Gun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcGunDao implements GunDao {
    /*private long id;
    private String model;
    private String origin;
    private Handy handy;
    private int firingRange;
    private int effectiveFiringRange;
    private Boolean cartridgeClipAvailability;
    private Boolean opticsAvailability;
    private String material;*/
    public static final String FIND_BY_ID = "SELECT * FROM GUNS WHERE id = ?";
    public static final String INSERT_INTO_GUNS = "INSERT INTO GUNS (id, model, origin, handy, firing_range, effective_firing_range, cartridge_clip_availability, optics_availability, material) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String ID = "id";
    public static final String MODEL = "model";
    public static final String ORIGIN = "origin";
    public static final String HANDY = "handy";
    public static final String FIRING_RANGE = "firing_Range";
    public static final String EFFECTIVE_FIRING_RANGE = "effective_Firing_Range";
    public static final String CARTRIDGE_CLIP_AVAILABILITY = "cartridge_Clip_Availability";
    public static final String OPTICS_AVAILABILITY = "optics_Availability";
    public static final String MATERIAL = "material";
    private final Connection connection;

    public JdbcGunDao(Connection connection) {
        this.connection = connection;
    }

    public Gun findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean found = resultSet.next();
            if (found) {
                Gun gun = new Gun();
                gun.setId(resultSet.getLong(ID));
                gun.setModel(resultSet.getString(MODEL));
                gun.setOrigin(resultSet.getString(ORIGIN));
                gun.setHandy(resultSet.getString(HANDY));
                gun.setFiringRange(resultSet.getInt(FIRING_RANGE));
                gun.setEffectiveFiringRange(resultSet.getInt(EFFECTIVE_FIRING_RANGE));
                gun.setCartridgeClipAvailability(resultSet.getBoolean(CARTRIDGE_CLIP_AVAILABILITY));
                gun.setOpticsAvailability(resultSet.getBoolean(OPTICS_AVAILABILITY));
                gun.setMaterial(resultSet.getString(MATERIAL));
                return gun;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void removeById(int id) {

    }

    public void update() {

    }

    public void save() {

    }

    public Gun merge() {
        return null;
    }

    public void insert(int id, String model, String origin, String handy, int firingRange, int effectiveFiringRange, boolean cartridgeClipAvailability, boolean opticsAvailability, String material) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_GUNS);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, origin);
            preparedStatement.setString(4, handy);
            preparedStatement.setInt(5, firingRange);
            preparedStatement.setInt(6, effectiveFiringRange);
            preparedStatement.setBoolean(7, cartridgeClipAvailability);
            preparedStatement.setBoolean(8, opticsAvailability);
            preparedStatement.setString(9, material);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }


        /*
        Connection connection = pool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PERSON (id, firstName, lastName) VALUES (DEFAULT, ?, ?)");
        preparedStatement.setString(1, gun.getModel());
        preparedStatement.setString(2, gun.getFiringRange());
        preparedStatement.executeUpdate();
        //TODO - make keys in BD and here
        generatedKeys.next();
        long id = generatedKeys.getLong(1);
        person.setId(id);*/
    }

    public void insert(Gun gun) {

    }

    public void upset() {

    }
}
