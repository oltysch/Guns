package com.epam.ot.dao;

import com.epam.ot.entity.Gun;

import java.sql.Connection;

public interface GunDao {

    Gun findById(int id);

    void update();

    Gun save();

    Gun merge();

    Gun insert();

    void upset();
}
