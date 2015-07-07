package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Admin on 04.07.2015.
 */
public class DOMGunParser implements GunParser {
    /**
     * @param input
     * @return Gun
     * parser is not finished yet
     */
    public Gun parse(InputStream input) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            //next code is not finished
            Document doc = builder.parse(input);
            Node node = doc.getChildNodes().item(0);
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodet = nodeList.item(i);
                System.out.println(nodet.getLocalName());
            }
            System.out.println(nodeList.toString());
            System.out.println(nodeList.getLength());
        } catch (ParserConfigurationException|SAXException|IOException e) {
            throw new ParseException(e);
        }
        return null;
    }
}
