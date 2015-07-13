package com.epam.ot.writer;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class JAXBGunWriter implements GunWriter {
    public void writeGun(File output, Gun gun) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Gun.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(gun, output);
        } catch (JAXBException e) {
            throw new ParseException(e);
        }
    }
}
