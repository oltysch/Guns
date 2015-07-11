package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 04.07.2015.
 */
public class JAXBGunParser implements GunParser {

    public List<Gun> parse(InputStream input) {
        List<Gun> guns = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Gun.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            guns.add((Gun) unmarshaller.unmarshal(input));
            return guns;
        } catch (JAXBException e) {
            throw new ParseException(e);
        }
    }

//    method is not finished
    public void writeGun(File output, Gun gun) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Gun.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(gun, output);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
