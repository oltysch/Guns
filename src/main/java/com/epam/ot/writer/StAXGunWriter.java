package com.epam.ot.writer;

import com.epam.ot.entity.Gun;
import com.epam.ot.exception.ParseException;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class StAXGunWriter implements GunWriter {
    public void writeGun(File output, Gun gun) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try {
            OutputStream outputStream = new FileOutputStream(output);
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(outputStream);
            serialize(writer, gun);
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new ParseException(e);
        }
    }

    private void serialize(XMLStreamWriter writer, Gun gun) {
        try {
            Method[] declaredMethods = gun.getClass().getDeclaredMethods();
            writer.writeStartDocument();
            writer.writeStartElement(getFirstLowerCase(gun.getClass().getSimpleName()));
            Map<String, Method> fieldsAndGetters = getMapGetters(declaredMethods);
            for (String field : fieldsAndGetters.keySet()) {
                try {
                    writer.writeStartElement(field);
                    writer.writeCharacters(fieldsAndGetters.get(field).invoke(gun).toString());
                    writer.writeEndElement();
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new ParseException(e);
                }
            }
            writer.writeEndElement();
            writer.writeEndDocument();
        } catch (XMLStreamException e) {
            throw new ParseException(e);
        }
    }

    private Map<String, Method> getMapGetters(Method[] declaredMethods) {
        Map<String, Method> fieldsAndGetters = new HashMap<>();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().substring(0, 3).equals("get")) {
                fieldsAndGetters.put(getFirstLowerCase(declaredMethod.getName().substring(3)), declaredMethod);
            }
        }
        return fieldsAndGetters;
    }

    private String getFirstLowerCase(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }
}
