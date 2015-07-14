package com.epam.ot.parser;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class StAXGunParserTest {
    public static final Logger logger = Logger.getLogger(StAXGunParserTest.class);
    GunParser gunParser;
    Gun gun;

    @Before
    public void setUp() throws Exception {
        gunParser = new StAXGunParser();
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

        assertNotNull(gun);
        assertNotNull(gun.getModel());
        assertNotNull(gun.getFiringRange());
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