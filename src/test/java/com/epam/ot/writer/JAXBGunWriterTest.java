package com.epam.ot.writer;

import com.epam.ot.entity.Gun;
import com.epam.ot.parser.JAXBGunParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class JAXBGunWriterTest {
//TODO - make tests
    Gun gun = new Gun();
    JAXBGunParser parser = new JAXBGunParser();
    JAXBGunWriter writer = new JAXBGunWriter();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWriteGun() throws Exception {
        InputStream input = new FileInputStream("123.xml");
        gun = parser.parseGun(input);
        writer.writeGun(new File("123.xml"), gun);
    }

    @Test
    public void testWriteGunOutput() throws Exception {
        InputStream input = new FileInputStream("123.xml");
//        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");

        gun = parser.parseGun(input);
        writer.writeGun(new File("123.xml"), gun);

        //test parse self output xml
        input = new FileInputStream("123.xml");
        gun = parser.parseGun(input);
        writer.writeGun(new File("456.xml"), gun);
    }
}