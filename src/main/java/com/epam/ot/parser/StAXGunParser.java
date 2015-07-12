package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;

import javax.xml.stream.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

/**
 * Created by Admin on 04.07.2015.
 */
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

    public void writeGun(File output, Gun gun) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try {
            OutputStream outputStream = new FileOutputStream(output);
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(outputStream);
            serialize(writer, gun);
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new ParseException(e);
        }
    }

    //    method is not finished
    private void serialize(XMLStreamWriter writer, Gun gun) {
        try {
            Field[] declaredFields = gun.getClass().getDeclaredFields();
            writer.writeStartDocument();
            writer.writeStartElement("gun");
            for (Field declaredField : declaredFields) {
                try {
                    String name = declaredField.getName();
                    writer.writeStartElement(name);
                    String method = "get"+name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
                    writer.writeCharacters(gun.getClass().getMethod(method).invoke(gun).toString());
                    writer.writeEndElement();
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            /*writer.writeStartDocument();

            writer.writeStartElement("gun");

            writer.writeStartElement("model");
            writer.writeCharacters(gun.getModel());
            writer.writeEndElement();

            writer.writeStartElement("origin");
            writer.writeCharacters(gun.getOrigin());
            writer.writeEndElement();

            writer.writeStartElement("handy");
            writer.writeCharacters(String.valueOf(gun.getHandy()));
            writer.writeEndElement();

            writer.writeStartElement("TTC");
            writer.writeStartElement("firingRange");
            writer.writeCharacters(String.valueOf(gun.getFiringRange()));
            writer.writeEndElement();
            writer.writeStartElement("effectiveFiringRange");
            writer.writeCharacters(String.valueOf(gun.getEffectiveFiringRange()));
            writer.writeEndElement();
            writer.writeStartElement("cartridgeClipAvailability");
            writer.writeCharacters(String.valueOf(gun.getCartridgeClipAvailability()));
            writer.writeEndElement();
            writer.writeStartElement("opticsAvailability");
            writer.writeCharacters(String.valueOf(gun.getOpticsAvailability()));
            writer.writeEndElement();
            writer.writeEndElement();

            writer.writeStartElement("material");
            writer.writeCharacters(gun.getMaterial());
            writer.writeEndElement();

            writer.writeEndDocument();*/
        } catch (XMLStreamException e) {
            throw new ParseException(e);
        }
    }

    private Gun process(XMLStreamReader reader) throws XMLStreamException {
        logger.debug("process launched");
        String name;
        Gun gun = new Gun();
        StringBuffer stringBuffer = new StringBuffer();
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    logger.debug("LocalName: " + reader.getLocalName());
                    logger.debug("namespaceURI: " + reader.getNamespaceURI());
                    logger.debug("prefix: " + reader.getPrefix());
                    stringBuffer.setLength(0);
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
                            gun.setCartridgeClipAvailability(Boolean.valueOf(stringBuffer.toString().trim()));
                            break;
                        case "opticsAvailability":
                            gun.setOpticsAvailability(Boolean.valueOf(stringBuffer.toString().trim()));
                            break;
                        case "material":
                            gun.setMaterial(stringBuffer.toString().trim());
                            break;
                        default:
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    logger.debug("characters: " + reader.getText());
                    stringBuffer.append(reader.getText());
                    break;
            }
        }
        return gun;
    }
}
