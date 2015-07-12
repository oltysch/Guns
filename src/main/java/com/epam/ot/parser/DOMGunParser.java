package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
    public Gun parseGun(InputStream input) {
        Gun gun;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(input);
            Element root = doc.getDocumentElement();
            gun = Analyzer.gunBuilder(root);
        } catch (ParserConfigurationException | SAXException | IOException | IllegalArgumentException e) {
            throw new ParseException(e);
        }
        return gun;
    }

    private static class Analyzer {
        public static Gun gunBuilder(Element root) {
            NodeList nodes = root.getElementsByTagName("gun");
            Gun gun = new Gun();
            for (int i = 0; i < nodes.getLength(); i++) {
                Element gunElement = (Element) nodes.item(i);
                //example without reduction
                gun.setModel(gunElement.getElementsByTagName("model").item(0).getFirstChild().getNodeValue());
                //examples with reduction
                gun.setOrigin(getBabyValue(gunElement, "origin"));
                gun.setHandy(Gun.Handy.valueOf(getBabyValue(gunElement, "handy")));
                gun.setMaterial(getBabyValue(gunElement, "material"));
                //getting weapon TTC's
                gun.setFiringRange(Integer.parseInt(getBabyValue(gunElement, "firingRange")));
                gun.setEffectiveFiringRange(Integer.parseInt(getBabyValue(gunElement, "effectiveFiringRange")));
                gun.setCartridgeClipAvailability(Boolean.valueOf(getBabyValue(gunElement, "cartridgeClipAvailability")));
                gun.setOpticsAvailability(Boolean.valueOf(getBabyValue(gunElement, "opticsAvailability")));
            }
            return gun;
        }

        private static String getBabyValue(Element parent, String childName) {
            Element child = getBaby(parent, childName);
            Node node = child.getFirstChild();
            String value = node.getNodeValue();
            return value;
        }

        private static Element getBaby(Element parent, String childName) {
            NodeList nList = parent.getElementsByTagName(childName);
            Element child = (Element) nList.item(0);
            return child;
        }
    }
}
