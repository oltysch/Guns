package com.epam.ot;

import com.epam.ot.parser.ListSAXParser;
import com.epam.ot.parser.SimpleSAXParser;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Admin on 28.06.2015.
 */
public class Main {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
//        test simple SAXParser
        javax.xml.parsers.SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        javax.xml.parsers.SAXParser sp = spf.newSAXParser();
        SimpleSAXParser handlerSimple = new SimpleSAXParser();
        InputStream inputStream = Main.class.getResourceAsStream("gun.xml");
        sp.parse(inputStream, handlerSimple);

//        test list SAXParser
        org.xml.sax.XMLReader parser = XMLReaderFactory.createXMLReader();
        parser.setFeature("http://xml.org/sax/features/validation", false);
        ListSAXParser handlerList = new ListSAXParser();
        parser.setContentHandler(handlerList);
        parser.setErrorHandler(handlerList);
        org.xml.sax.InputSource input = new org.xml.sax.InputSource(inputStream);
        parser.parse(input);
    }
}