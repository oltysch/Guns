package com.epam.ot.writer;

import com.epam.ot.entity.Gun;
import com.epam.ot.parser.ParserException;
import com.epam.ot.parser.GunParser;
import com.epam.ot.parser.JAXBGunParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class JAXBGunWriterTest {
    Gun gun;
    GunParser parser;
    GunWriter writer;

    @Before
    public void setUp() throws Exception {
        gun = new Gun();
        parser = new JAXBGunParser();
        writer = new JAXBGunWriter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWriteGun() throws Exception {
//        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");
//TODO delete this bottom string after fix JAXB Parser
        InputStream input = new FileInputStream("123.xml");

        Throwable throwable = null;
        gun = parser.parseGun(input);
        try {
            writer.writeGun(new File("123.xml"), gun);
        } catch (ParserException e) {
            throwable = e;
        }

        assertNull(throwable);
    }

    @Test
    public void testWriteGunOutput() throws Exception {
//        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");
//TODO delete this bottom string after fix JAXB Parser
        InputStream input = new FileInputStream("123.xml");

        gun = parser.parseGun(input);
        writer.writeGun(new File("123.xml"), gun);

        //test parse self output xml
        input = new FileInputStream("123.xml");
        Throwable throwable = null;
        try {
            Gun gun2 = parser.parseGun(input);
            writer.writeGun(new File("456.xml"), gun);
            assertEquals(gun, gun2);
        } catch (ParserException e) {
            throwable = e;
        }

        assertNull(throwable);
    }

    @Test
    public void shouldThrowExceptionForNullOutput() {
        //TODO make this test
    }
}