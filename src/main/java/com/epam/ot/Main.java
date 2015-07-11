package com.epam.ot;

import com.epam.ot.entity.Gun;
import com.epam.ot.parser.JAXBGunParser;
import com.epam.ot.parser.StAXGunParser;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JAXBGunParser parser = new JAXBGunParser();
        InputStream input = Main.class.getClassLoader().getResourceAsStream("123.xml");
        List<Gun> gunList = new ArrayList<>();
        gunList.addAll(parser.parse(input));
        parser.writeGun(new File("123.xml"), gunList.get(0));
    }
}