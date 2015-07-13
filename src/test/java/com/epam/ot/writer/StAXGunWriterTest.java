package com.epam.ot.writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StAXGunWriterTest {
//    TODO - make tests
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWriteGun() throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");

        gun = gunParser.parseGun(input);
        gunParser.writeGun(new File("123.xml"), gun);
    }

    @Test
    public void testWriteGunOutput() throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");

        gun = gunParser.parseGun(input);
        gunParser.writeGun(new File("123.xml"), gun);

        //test parse self output xml
        input = new FileInputStream("123.xml");
        gun = gunParser.parseGun(input);
        gunParser.writeGun(new File("456.xml"), gun);
    }
}