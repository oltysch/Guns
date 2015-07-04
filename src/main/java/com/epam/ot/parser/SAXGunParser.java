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

/**
 * Created by Admin on 29.06.2015.
 */
public class SAXGunParser implements GunParser {
    public static final Logger logger = Logger.getLogger(SAXGunParser.class);

    public Gun parse(InputStream input) {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        Gun gun;
        try {
            SAXParser parser = spf.newSAXParser();
            Handler handler = new Handler();
            parser.parse(input, handler);
            gun = handler.getGun();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParseException(e);
        }
        return gun;
    }

    class Handler extends DefaultHandler {
        StringBuffer accumulator = new StringBuffer();
        Gun gun;
        String model;
        Gun.Handy handy;
        String origin;
        String material;
        private int firingRange;
        private int effectiveFiringRange;
        private Boolean cartridgeClip;
        private Boolean optics;

        public Gun getGun() {
            return gun;
        }

        public void endDocument() throws SAXException {
            gun = new Gun(model, origin, handy, firingRange, effectiveFiringRange, cartridgeClip, optics, material);
            logger.info("parsing ended");
        }

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            accumulator.setLength(0);
        }

        public void characters(char[] buffer, int start, int length) {
            accumulator.append(buffer, start, length);
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
            logger.debug("QName: \"" + qName + "\". Value: \"" + accumulator.toString().trim() + "\"");
            switch (qName) {
                case "Model":
                    model = accumulator.toString().trim();
                    logger.debug("chars1: " + accumulator.toString());
                    break;
                case "Handy":
                    handy = Gun.Handy.valueOf(accumulator.toString().trim());
                    break;
                case "Origin":
                    origin = accumulator.toString().trim();
                    break;
                case "FiringRange":
                    firingRange = Integer.parseInt(accumulator.toString().trim());
                    break;
                case "EffectiveFiringRange":
                    effectiveFiringRange = Integer.parseInt(accumulator.toString().trim());
                    break;
                case "CartridgeClipAvailability":
                    cartridgeClip = Boolean.valueOf(accumulator.toString().trim());
                    break;
                case "OpticsAvailability":
                    optics = Boolean.valueOf(accumulator.toString().trim());
                    break;
                case "Material":
                    material = accumulator.toString().trim();
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
