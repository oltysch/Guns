package com.epam.ot.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Admin on 29.06.2015.
 */
public class SimpleSAXParser extends DefaultHandler {
    StringBuffer accumulator = new StringBuffer();
    String testValue;
    String gunModel;
    String gunHandy;
    String gunOrigin;

    public void endDocument() throws SAXException {
        System.out.println("Parsing ended");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        accumulator.setLength(0);
        if (qName.equals("Model")) {
            testValue = attributes.getValue("testAttribute");
        }
    }

    public void characters(char[] buffer, int start, int length) {
        accumulator.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("Model")) {
            gunModel = accumulator.toString().trim();
        } else if (qName.equals("Handy")) {
            gunHandy = accumulator.toString().trim();
        } else if (qName.equals("Origin")) {
            gunOrigin = accumulator.toString().trim();
        } else if (qName.equals("Gun")) {
            System.out.println("Gun: \ntestValue: " + testValue + "\nModel: " + gunModel + "\nHandy: " + gunHandy + "\nOrigin: " + gunOrigin);
        }
    }

    public void startDocument() throws SAXException {
        System.out.println("parsing started");
    }

    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXParseException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": " + exception.getMessage());
        throw (exception);
    }
}
