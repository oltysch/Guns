package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

/**
 * Created by Admin on 29.06.2015.
 */
public class ListSAXParser extends DefaultHandler {
//    HashMap nameToClass;
//    HashMap nameToPatterns;
    StringBuffer accumulator;
    List<Gun> gunList;
//    HashMap gunMap;
    String testValue, gunModel, gunHandy, gunOrigin, gunMaterial;

    public void startDocument() {
        accumulator = new StringBuffer();
        gunList = new ArrayList<Gun>();
//        gunMap = new HashMap();
//        nameToClass = new HashMap();
//        nameToPatterns = new HashMap();
    }

    public void characters(char[] buffer, int start, int length) {
        accumulator.append(buffer, start, length);
    }

    public void startElement(String namespaceURL, String localName, String qname, Attributes attributes) {
        accumulator.setLength(0);
    }

    public void endElement(String namespaceURL, String localName, String qname) {
        if (localName.equals("Model")) {
            gunModel = accumulator.toString().trim();
        } else if (localName.equals("Handy")) {
            gunHandy = accumulator.toString().trim();
        } else if (localName.equals("Origin")) {
            gunOrigin = accumulator.toString().trim();
        } else if (localName.equals("Material")) {
            gunMaterial = accumulator.toString().trim();
        } else if (localName.equals("Gun")) {
            gunList.add(new Gun(gunModel, gunOrigin, gunMaterial));
        }
    }

    public void endDocument() {
//        List gunNames = new ArrayList(gunMap.keySet());
//        Collections.sort(gunNames);
        for (Gun gun:gunList) {
//            String name = (String) iterator.next();
//            String classname = (String) nameToClass.get(name);
//            List patterns = (List) nameToPatterns.get(name);
            String model = gun.getModel();
            String origin = gun.getOrigin();
            String material = gun.getMaterial();
            System.out.println("Model: " + model+"\nOrigin: "+origin+"\nMaterial: "+material);
//            System.out.println("Class: " + classname);
            /*if (patterns != null) {
                System.out.println("Patterns:");
                for (Iterator i = patterns.iterator(); i.hasNext();) {
                    System.out.println("\t" + i.next());
                }
            }*/
            System.out.println();
        }
    }

    public void warning(SAXParseException exception) {
        System.err.println("WARNING: line " + exception.getLineNumber() + ": " + exception.getMessage());
    }

    public void error(SAXParseException exception) {
        System.err.println("ERROR: line " + exception.getLineNumber() + ": "+exception.getMessage());
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        System.err.println("FATAL: line " + exception.getLineNumber() + ": "+exception.getMessage());
        throw (exception);
    }
}
