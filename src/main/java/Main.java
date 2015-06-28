import com.sun.beans.decoder.DocumentHandler;
import jdk.internal.org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

/**
 * Created by Admin on 28.06.2015.
 */
public class Main {
    public static void main(String[] args) throws SAXException, IOException {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        org.xml.sax.InputSource input = new org.xml.sax.InputSource(Main.class.getClassLoader().getResourceAsStream("gun.xml"));
        parser.parse(input);
    }
}