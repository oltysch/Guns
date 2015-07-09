package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Created by Admin on 09.07.2015.
 */
public class DOMGunParserTest {
    GunParser gunParser;
    List<Gun> gunList;
    public static final Logger logger =Logger.getLogger(SAXGunParserTest.class);

    @Before
    public void setUp() throws Exception {
        gunParser = new DOMGunParser();
        gunList = new ArrayList<>();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldThrowExceptionForNullInputStream() throws Exception {
        InputStream input = null;

        Throwable throwable = null;
        try {
            gunList.addAll(gunParser.parse(input));
        }catch (Throwable t) {
            throwable = t;
        }

        assertThat(throwable, instanceOf(ParseException.class));
    }

    @Test
    public void shouldParseGoodXML() throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");

        gunList.addAll(gunParser.parse(input));
        for (Gun gun : gunList) {
            logger.info(gun);
        }

        assertEquals("Size of gun list should be equal 2", 2, gunList.size());
    }
}