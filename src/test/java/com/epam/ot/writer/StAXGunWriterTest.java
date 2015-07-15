package com.epam.ot.writer;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;
import com.epam.ot.parser.GunParser;
import com.epam.ot.parser.StAXGunParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class StAXGunWriterTest {
    Gun gun;
    GunParser parser;
    GunWriter writer;

    @Before
    public void setUp() throws Exception {
        gun = new Gun();
        parser = new StAXGunParser();
        writer = new StAXGunWriter();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testWriteGun() throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");

        //TODO make a gun factory for this
        gun = parser.parseGun(input);

        Throwable throwable = null;
        try {
            writer.writeGun(new File("123.xml"), gun);
        } catch (ParseException e) {
            throwable = e;
        }

        assertNull(throwable);
    }

    @Test
    public void testWriteGunOutput() throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream("gun.xml");

        gun = parser.parseGun(input);
        writer.writeGun(new File("123.xml"), gun);

        //test parse self output xml
        input = new FileInputStream("123.xml");
        Throwable throwable = null;
        try {
            Gun gun2 = parser.parseGun(input);
            writer.writeGun(new File("456.xml"), gun);
            assertEquals(gun.getModel(), gun2.getModel());
            assertEquals(gun.getFiringRange(), gun2.getFiringRange());
        } catch (ParseException e) {
            throwable = e;
        }

        assertNull(throwable);
    }

    @Test
    public void shouldThrowExceptionForNullOutput() {
        //TODO make this test
    }
}