package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JAXBGunParserTest {
    JAXBGunParser gunParser;
    List<Gun> gunList;
    public static final Logger logger =Logger.getLogger(SAXGunParserTest.class);

    @Before
    public void setUp() throws Exception {
        gunParser = new JAXBGunParser();
        gunList = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParse() throws Exception {

    }

    @Test
    public void testWriteGun() throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");

        gunList.addAll(gunParser.parse(input));
        gunParser.writeGun(new File("123.xml"), gunList.get(0));
    }
}