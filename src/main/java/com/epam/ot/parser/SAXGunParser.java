package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

public class SAXGunParser implements GunParser {
    public static final Logger logger = Logger.getLogger(SAXGunParser.class);

    public Gun parseGun(InputStream input) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        Gun gun;
        try {
            SAXParser parser = spf.newSAXParser();
            Handler handler = new Handler();
            parser.parse(input, handler);
            gun = handler.getGun();
        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException e) {
            throw new ParseException(e);
        }
        return gun;
    }

    class Handler extends DefaultHandler {
        StringBuffer accumulator = new StringBuffer();
        Gun gun = new Gun();

        public Gun getGun() {
            return gun;
        }

        public void endDocument() throws SAXException {
//            gun = new Gun(model, origin, handy, firingRange, effectiveFiringRange, cartridgeClip, optics, material);
            logger.info("parsing ended");
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            accumulator.setLength(0);
            if (qName.equals("gun")) gun = new Gun();
        }

        public void characters(char[] buffer, int start, int length) {
            accumulator.append(buffer, start, length);
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            logger.debug("QName: \"" + qName + "\". Value: \"" + accumulator.toString().trim() + "\"");
            switch (qName) {
                case "model":
                    gun.setModel(accumulator.toString().trim());
                    logger.debug("chars1: " + accumulator.toString());
                    break;
                case "handy":
                    gun.setHandy(accumulator.toString().trim());
                    break;
                case "origin":
                    gun.setOrigin(accumulator.toString().trim());
                    break;
                case "firingRange":
                    gun.setFiringRange(Integer.parseInt(accumulator.toString().trim()));
                    break;
                case "effectiveFiringRange":
                    gun.setEffectiveFiringRange(Integer.parseInt(accumulator.toString().trim()));
                    break;
                case "cartridgeClipAvailability":
                    gun.setCartridgeClipAvailability(Boolean.valueOf(accumulator.toString().trim()));
                    break;
                case "opticsAvailability":
                    gun.setOpticsAvailability(Boolean.valueOf(accumulator.toString().trim()));
                    break;
                case "material":
                    gun.setMaterial(accumulator.toString().trim());
                    break;
                default:
            }
        }

        public void startDocument() throws SAXException {
            logger.info("parsing started");
        }

        public void warning(SAXParseException exception) {
            logger.warn("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
        }

        public void error(SAXParseException exception) {
            logger.error("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
        }

        public void fatalError(SAXParseException exception) throws SAXParseException {
            logger.fatal("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
            throw (exception);
        }
    }
}
