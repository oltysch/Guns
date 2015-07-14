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
            //TODO create Map of fields
            //TODO change the logic on: receive getters - and use it for getting values.
            Field[] declaredFields = gun.getClass().getDeclaredFields();
            writer.writeStartDocument();
            writer.writeStartElement("gun");
            for (Field declaredField : declaredFields) {
                try {
                    String name = declaredField.getName();
                    writer.writeStartElement(name);
                    String method = "get"+name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
                    writer.writeCharacters(gun.getClass().getMethod(method).invoke(gun).toString());
                    writer.writeEndElement();
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new ParseException(e);
                }
            }
            writer.writeEndElement();
            writer.writeEndDocument();
        } catch (XMLStreamException e) {
            throw new ParseException(e);
        }
    }
}
