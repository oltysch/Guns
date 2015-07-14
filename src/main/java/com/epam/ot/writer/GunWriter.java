package com.epam.ot.writer;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;

import java.io.File;

public interface GunWriter {
    void writeGun(File output, Gun gun) throws ParseException;
}
