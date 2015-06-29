package utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

/**
 * Created by Admin on 29.06.2015.
 */
public class ListSAXParser extends DefaultHandler {
    HashMap nameToClass;
    HashMap nameToPatterns;
    StringBuffer accumulator;
    String testValue, gunModel, gunHandy, gunOrigin;

    public void startDocument() {
        accumulator = new StringBuffer();
        nameToClass = new HashMap();
        nameToPatterns = new HashMap();
    }

    public void characters(char[] buffer, int start, int length) {
        accumulator.append(buffer, start, length);
    }

    public void startElement(String namespaceURL, String localName, String qname, Attributes attributes) {
        accumulator.setLength(0);
    }

    public void endElement(String namescaceURL, String localName, String qname) {
        if (localName.equals("Model")) {
            gunModel = accumulator.toString().trim();
        } else if (localName.equals("Handy")) {
            gunHandy = accumulator.toString().trim();
        } else if (localName.equals("Origin")) {
            gunOrigin = accumulator.toString().trim();
            //NEXT ACTIONS JUST COPYPASTED.
        } else if (localName.equals("servlet")) {
            nameToClass.put(servletName, servletClass);
        } else if (localName.equals("servlet-mapping")) {
            List patterns = (List) nameToPatterns.get(servletName);
            if (patterns == null) {
                patterns = new ArrayList();
                nameToPatterns.put(servletName, patterns);
            }
            patterns.add(servletPattern);
        }
    }

    public void endDocument() {
        List gunNames = new ArrayList(nameToClass.keySet());
        Collections.sort(gunNames);
        for (Iterator iterator = gunNames.iterator(); iterator.hasNext();) {
            String name = (String) iterator.next();
            String classname = (String) nameToClass.get(name);
            List patterns = (List) nameToPatterns.get(name);
            System.out.println("Name: " + name);
            System.out.println("Class: " + classname);
            if (patterns != null) {
                System.out.println("Patterns:");
                for (Iterator i = patterns.iterator(); i.hasNext();) {
                    System.out.println("\t" + i.next());
                }
            }
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
