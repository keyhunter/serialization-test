package com.keyhunter.test.serialization.jdk;

import com.keyhunter.test.serialization.Serializer;

import java.io.*;

/**
 * JDK自带的序列化
 * Created by yingren on 2016/12/2.
 */
public class JdkSerializer implements Serializer {

    public <T> byte[] serialize(T object) {
        byte[] bytes = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(out)) {
            objectOutputStream.writeObject(object);
            bytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            Object object = objectInputStream.readObject();
            return (T) object;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
