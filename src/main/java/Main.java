import com.sun.beans.decoder.DocumentHandler;
import jdk.internal.org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import utils.SimpleSAXParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

/**
 * Created by Admin on 28.06.2015.
 */
public class Main {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
//        test simple parser
        javax.xml.parsers.SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setValidating(false);
        javax.xml.parsers.SAXParser sp = spf.newSAXParser();
        SimpleSAXParser handler = new SimpleSAXParser();
        InputStream inputStream = Main.class.getResourceAsStream("gun.xml");
        sp.parse(inputStream, handler);
    }
}