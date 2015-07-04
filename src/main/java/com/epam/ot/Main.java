package com.epam.ot;

import com.epam.ot.entity.Gun;
import com.epam.ot.parser.JAXBGunParser;
import com.epam.ot.parser.JAXPGunParser;
import com.epam.ot.parser.SAXGunParser;
import com.epam.ot.parser.StAXGunParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.InputStream;

import org.apache.log4j.Logger;
/**
 * Created by Admin on 28.06.2015.
 */
public class Main {
    public static final Logger logger =Logger.getLogger(Main.class);
    public static void main(String[] args) throws JAXBException {
        InputStream input = Main.class.getClassLoader().getResourceAsStream("gun.xml");
//        test simple SAXGunParser
        System.out.println("SimpleParser");
        SAXGunParser saxGunParser = new SAXGunParser();
        StAXGunParser stAXGunParser = new StAXGunParser();
        JAXPGunParser jaxpGunParser = new JAXPGunParser();
        JAXBGunParser jaxbGunParser = new JAXBGunParser();
        Gun gun = saxGunParser.parse(input);
        System.out.println(gun.gunToString());

/*//        serialization in XML
        File f = new File("1.xml");

        //need move this inside new parser - returns xml or InputStream
        JAXBContext context = JAXBContext.newInstance(Gun.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(gun, f);*/
    }
}