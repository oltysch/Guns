package com.epam.ot;

import com.epam.ot.dao.DaoFactory;
import com.epam.ot.dao.GunDao;
import com.epam.ot.entity.Gun;

public class DaoRunner {
    public static void main(String[] args) {
        //TODO make work with BD
        DaoFactory daoFactory = DaoFactory.getInstance();
        GunDao gunDao = daoFactory.createGunDao();
        Gun gun = gunDao.findById(1);
        gunDao.insert(6, "five seven", "USA", "One_handed", 200, 100, true, false, "plastic");
        gun.setMaterial("Plastic");
        System.out.println(gun);
        /*gunDao.update();
        gunDao.save();
        gunDao.merge();
        gunDao.upset();
        gunDao.insert();*/
    }
}
