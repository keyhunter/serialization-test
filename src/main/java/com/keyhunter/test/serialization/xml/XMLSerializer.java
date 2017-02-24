package com.keyhunter.test.serialization.xml;

import com.keyhunter.test.serialization.Serializer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @auther yingren
 * Created on 2016/12/26.
 */
public class XMLSerializer implements Serializer {


    @Override
    public <T> byte[] serialize(T object) {

        ByteArrayOutputStream os = null;
        XMLEncoder xe = null;
        try {
            os = new ByteArrayOutputStream();
            xe = new XMLEncoder(os, "UTF-8", true, 0);
            xe.writeObject(object);    // 序列化成XML字符串
        } finally {
            if (xe != null) {
                xe.close();
            }
        }
        return os.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes); XMLDecoder xd = new XMLDecoder(in)) {
            Object obj = xd.readObject();       // 从XML序列中解码为Java对象
            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
