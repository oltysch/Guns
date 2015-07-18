package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;

import javax.xml.stream.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

public class StAXGunParser implements GunParser {
    public static final Logger logger = Logger.getLogger(StAXGunParser.class);

    public Gun parseGun(InputStream input) {
        logger.debug("started parsing");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        logger.debug("factory prepared");
        try {
            logger.debug("reader preparing in process");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            logger.debug("reader prepared");
            return process(reader);
        } catch (XMLStreamException e) {
            throw new ParseException(e);
        }
    }

    private Gun process(XMLStreamReader reader) throws XMLStreamException {
        logger.debug("process launched");
        String name;
        Gun gun = new Gun();
        StringBuilder stringBuilder = new StringBuilder();
        while (reader.hasNext()) {
            int type = reader.next();
            //TODO - replase this switches by reflections
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    logger.debug("LocalName: " + reader.getLocalName());
                    logger.debug("namespaceURI: " + reader.getNamespaceURI());
                    logger.debug("prefix: " + reader.getPrefix());
                    stringBuilder.setLength(0);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    logger.debug("end element: " + reader.getLocalName() + " characters: " + stringBuilder.toString());
                    switch (reader.getLocalName()) {
                        case "model":
                            gun.setModel(stringBuilder.toString().trim());
                            logger.debug("chars1: " + stringBuilder.toString());
                            break;
                        case "handy":
                            gun.setHandy(stringBuilder.toString().trim());
                            break;
                        case "origin":
                            gun.setOrigin(stringBuilder.toString().trim());
                            break;
                        case "firingRange":
                            gun.setFiringRange(Integer.parseInt(stringBuilder.toString().trim()));
                            break;
                        case "effectiveFiringRange":
                            gun.setEffectiveFiringRange(Integer.parseInt(stringBuilder.toString().trim()));
                            break;
                        case "cartridgeClipAvailability":
                            gun.setCartridgeClipAvailability(Boolean.valueOf(stringBuilder.toString().trim()));
                            break;
                        case "opticsAvailability":
                            gun.setOpticsAvailability(Boolean.valueOf(stringBuilder.toString().trim()));
                            break;
                        case "material":
                            gun.setMaterial(stringBuilder.toString().trim());
                            break;
                        default:
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    logger.debug("characters: " + reader.getText());
                    stringBuilder.append(reader.getText());
                    break;
            }
        }
        return gun;
    }
}
