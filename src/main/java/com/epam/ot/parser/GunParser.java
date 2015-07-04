package com.epam.ot.parser;

import com.epam.ot.entity.Gun;

import java.io.InputStream;

/**
 * Created by Admin on 04.07.2015.
 */
public interface GunParser {
    public Gun parse(InputStream input);
}
