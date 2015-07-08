package com.epam.ot;

import com.epam.ot.entity.Gun;
import com.epam.ot.parser.DOMGunParser;
import com.epam.ot.parser.JAXBGunParser;
import com.epam.ot.parser.SAXGunParser;
import com.epam.ot.parser.StAXGunParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by Admin on 28.06.2015.
 */
public class Main {
    public static final Logger logger =Logger.getLogger(Main.class);
    public static void main(String[] args) throws JAXBException, IOException {
        InputStream input = Main.class.getClassLoader().getResourceAsStream("gun.xml");
//        test simple SAXGunParser
        System.out.println("SimpleParser");
        SAXGunParser saxGunParser = new SAXGunParser();
        StAXGunParser staxGunParser = new StAXGunParser();
        DOMGunParser domGunParser = new DOMGunParser();
        JAXBGunParser jaxbGunParser = new JAXBGunParser();
        List<Gun> gunList= new ArrayList<>();
        logger.debug("sax parser");
        gunList.addAll(saxGunParser.parse(input));
        input = Main.class.getClassLoader().getResourceAsStream("gun.xml");
        logger.debug("stax parser");
        gunList.addAll(staxGunParser.parse(input));
        input = Main.class.getClassLoader().getResourceAsStream("gun.xml");
        logger.debug("dom parser");
        gunList.addAll(domGunParser.parse(input));
//        gunList.add(jaxbGunParser.parse(input));
        for (Gun gun : gunList) {
            System.out.println(gun.gunToString());
        }

/*//        serialization in XML

        File f = new File("1.xml");

        //need move this inside new parser - returns xml or InputStream
        JAXBContext context = JAXBContext.newInstance(Gun.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(gun, f);*/
    }
}