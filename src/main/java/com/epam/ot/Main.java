package com.epam.ot;

import com.epam.ot.entity.Gun;
import com.epam.ot.parser.JAXBGunParser;
import com.epam.ot.parser.StAXGunParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JAXBGunParser jaxbGunParser = new JAXBGunParser();
        try {
            InputStream input = new FileInputStream("123.xml");
            jaxbGunParser.parseGun(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}