package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;

import javax.xml.bind.*;
import java.io.File;
import java.io.InputStream;

public class JAXBGunParser implements GunParser {

    public Gun parseGun(InputStream input) {
        Gun gun;
        try {
            JAXBContext context = JAXBContext.newInstance(Gun.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            gun = (Gun) unmarshaller.unmarshal(input);
            return gun;
        } catch (JAXBException | IllegalArgumentException e) {
            throw new ParseException(e);
        }
    }
}
