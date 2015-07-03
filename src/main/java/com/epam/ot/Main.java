package com.epam.ot;

import com.epam.ot.entity.Gun;
import com.epam.ot.parser.SAXParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.logging.XMLFormatter;

import org.apache.log4j.Logger;
/**
 * Created by Admin on 28.06.2015.
 */
public class Main {
    public static final Logger logger =Logger.getLogger(Main.class);
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, JAXBException {
        InputSource input = new InputSource("src/main/resources/gun.xml");
//        test simple SAXParser
        System.out.println("SimpleParser");
        javax.xml.parsers.SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        javax.xml.parsers.SAXParser parser = spf.newSAXParser();
        SAXParser handler = new SAXParser();
//        InputStream inputStream = Main.class.getResourceAsStream("gun.xml");
//        InputStream inputStream = Main.class.getResource("gun.xml");
        parser.parse(input, handler); //move this inside gunParser
        Gun gun = handler.getGun();
        System.out.println(gun.gunToString());

//        serialization
        File f = new File("1.xml");
        
        //move inside new parser - returns xml or InputStream
        JAXBContext context = JAXBContext.newInstance(Gun.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(gun, f);

//        test list SAXParser
//        System.out.println("\n\nListParser");
//        org.xml.sax.XMLReader parser = XMLReaderFactory.createXMLReader();
//        parser.setFeature("http://xml.org/sax/features/validation", false);
//        ListSAXParser handlerList = new ListSAXParser();
//        parser.setContentHandler(handlerList);
//        parser.setErrorHandler(handlerList);
//        InputSource input = new InputSource(inputStream);
//        parser.parse(input);
    }
}