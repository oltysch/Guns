package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;
import jdk.nashorn.internal.runtime.ParserException;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 29.06.2015.
 */
public class SAXGunParser implements GunParser {
    public static final Logger logger = Logger.getLogger(SAXGunParser.class);

    public List<Gun> parse(InputStream input) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        List<Gun> guns;
        try {
            SAXParser parser = spf.newSAXParser();
            Handler handler = new Handler();
            parser.parse(input, handler);
            guns = handler.getGuns();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParseException(e);
        }
        return guns;
    }

    class Handler extends DefaultHandler {
        StringBuffer accumulator = new StringBuffer();
        List<Gun> guns = new ArrayList<>();
        Gun gun = new Gun();
//        String model;
//        Gun.Handy handy;
//        String origin;
//        String material;
//        private int firingRange;
//        private int effectiveFiringRange;
//        private Boolean cartridgeClip;
//        private Boolean optics;

        public List<Gun> getGuns() {
            return guns;
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
//                    model = accumulator.toString().trim();
                    gun.setModel(accumulator.toString().trim());
                    logger.debug("chars1: " + accumulator.toString());
                    break;
                case "handy":
//                    handy = Gun.Handy.valueOf(accumulator.toString().trim());
                    gun.setHandy(Gun.Handy.valueOf(accumulator.toString().trim()));
                    break;
                case "origin":
//                    origin = accumulator.toString().trim();
                    gun.setOrigin(accumulator.toString().trim());
                    break;
                case "firingRange":
//                    firingRange = Integer.parseInt(accumulator.toString().trim());
                    gun.setFiringRange(Integer.parseInt(accumulator.toString().trim()));
                    break;
                case "effectiveFiringRange":
//                    effectiveFiringRange = Integer.parseInt(accumulator.toString().trim());
                    gun.setEffectiveFiringRange(Integer.parseInt(accumulator.toString().trim()));
                    break;
                case "cartridgeClipAvailability":
//                    cartridgeClip = Boolean.valueOf(accumulator.toString().trim());
                    gun.setCartridgeClip(Boolean.valueOf(accumulator.toString().trim()));
                    break;
                case "opticsAvailability":
//                    optics = Boolean.valueOf(accumulator.toString().trim());
                    gun.setOptics(Boolean.valueOf(accumulator.toString().trim()));
                    break;
                case "material":
//                    material = accumulator.toString().trim();
                    gun.setMaterial(accumulator.toString().trim());
                    break;
                case "gun":
                    guns.add(gun);
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
