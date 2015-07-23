package com.epam.ot.parser;

import com.epam.ot.entity.Gun;

import java.io.InputStream;

public interface GunParser {
    Gun parseGun(InputStream input) throws ParserException;
}
