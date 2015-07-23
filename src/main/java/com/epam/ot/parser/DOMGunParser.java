package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
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
            throw new ParserException(e);
        }
        return gun;
    }

    private static class Analyzer {
        public static Gun gunBuilder(Element root) {
            Gun gun = new Gun();
            //example without reduction
            gun.setModel(root.getElementsByTagName("model").item(0).getFirstChild().getNodeValue());
            //examples with reduction
            gun.setOrigin(getBabyValue(root, "origin"));
            gun.setHandy(getBabyValue(root, "handy"));
            gun.setMaterial(getBabyValue(root, "material"));
            //getting weapon TTC's
            gun.setFiringRange(Integer.parseInt(getBabyValue(root, "firingRange")));
            gun.setEffectiveFiringRange(Integer.parseInt(getBabyValue(root, "effectiveFiringRange")));
            gun.setCartridgeClipAvailability(Boolean.valueOf(getBabyValue(root, "cartridgeClipAvailability")));
            gun.setOpticsAvailability(Boolean.valueOf(getBabyValue(root, "opticsAvailability")));
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
