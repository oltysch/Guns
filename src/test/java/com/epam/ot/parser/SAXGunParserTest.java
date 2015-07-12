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

public class SAXGunParserTest {
    public static final Logger logger = Logger.getLogger(SAXGunParserTest.class);
    GunParser gunParser;
    Gun gun;

    @Before
    public void setUp() throws Exception {
        gunParser = new SAXGunParser();
        gun = new Gun();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testParse() throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");

        gun = gunParser.parseGun(input);
        logger.info("\n" + gun);
    }

    @Test
    public void shouldThrowExceptionForNullInputStream() throws Exception {
        InputStream input = null;

        Throwable throwable = null;
        try {
            gun = gunParser.parseGun(input);
        } catch (Throwable t) {
            throwable = t;
        }

        assertThat(throwable, instanceOf(ParseException.class));
    }
}