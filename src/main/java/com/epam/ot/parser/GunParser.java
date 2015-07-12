package com.epam.ot.parser;

import com.epam.ot.entity.Gun;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Admin on 04.07.2015.
 */
public interface GunParser {
    public Gun parseGun(InputStream input);
}
