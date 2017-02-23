package com.keyhunter.test.serialization.xml;

import com.keyhunter.test.serialization.Serializer;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @auther jiujie
 * Created on 2016/12/26.
 */
public class XMLSerializer implements Serializer {


    @Override
    public <T> byte[] serialize(T object) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        XMLEncoder xe = new XMLEncoder(os, "UTF-8", true, 0);     // 仅用于Java SE 7
        xe.writeObject(object);    // 序列化成XML字符串
        xe.close();
        return os.toByteArray();
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        XMLDecoder xd = new XMLDecoder(new ByteArrayInputStream(bytes));
        Object obj = xd.readObject();       // 从XML序列中解码为Java对象
        xd.close();
        return (T)obj;
    }
}
