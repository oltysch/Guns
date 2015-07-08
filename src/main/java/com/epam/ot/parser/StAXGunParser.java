package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;

import javax.xml.stream.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
/**
 * Created by Admin on 04.07.2015.
 */
public class StAXGunParser implements GunParser{
    public static final Logger logger =Logger.getLogger(StAXGunParser.class);

    public List<Gun> parse(InputStream input) {
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

    public void serialize(Gun gun) {
    }

    private List<Gun> process(XMLStreamReader reader) throws XMLStreamException {
        logger.debug("process launched");
        String name;
        List<Gun> guns = new ArrayList<>();
        Gun gun = new Gun();
        StringBuffer stringBuffer = new StringBuffer();
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    logger.debug("start element: " + reader.getLocalName());
                    stringBuffer.setLength(0);
                    if (reader.getLocalName().equals("gun")) gun = new Gun();
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    logger.debug("end element: " + reader.getLocalName() + " characters: " + stringBuffer.toString());
                    switch (reader.getLocalName()) {
                        case "model":
                            gun.setModel(stringBuffer.toString().trim());
                            logger.debug("chars1: " + stringBuffer.toString());
                            break;
                        case "handy":
                            gun.setHandy(Gun.Handy.valueOf(stringBuffer.toString().trim()));
                            break;
                        case "origin":
                            gun.setOrigin(stringBuffer.toString().trim());
                            break;
                        case "firingRange":
                            gun.setFiringRange(Integer.parseInt(stringBuffer.toString().trim()));
                            break;
                        case "effectiveFiringRange":
                            gun.setEffectiveFiringRange(Integer.parseInt(stringBuffer.toString().trim()));
                            break;
                        case "cartridgeClipAvailability":
                            gun.setCartridgeClip(Boolean.valueOf(stringBuffer.toString().trim()));
                            break;
                        case "opticsAvailability":
                            gun.setOptics(Boolean.valueOf(stringBuffer.toString().trim()));
                            break;
                        case "material":
                            gun.setMaterial(stringBuffer.toString().trim());
                            break;
                        case "gun":
                            guns.add(gun);
                        default:
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    logger.debug("characters: " + reader.getText());
                    stringBuffer.append(reader.getText());
                    break;
            }
        }
        return guns;
    }
}
