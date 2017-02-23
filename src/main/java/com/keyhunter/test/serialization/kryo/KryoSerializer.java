package com.keyhunter.test.serialization.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.keyhunter.test.serialization.Serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @auther yingren
 * Created on 2017/2/23.
 */
public class KryoSerializer implements Serializer {

    private Kryo kryo = new Kryo();

    @Override
    public <T> byte[] serialize(T object) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             Output output = new Output(outputStream)) {
            kryo.writeObject(output, object);
            output.flush();
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream);) {
            return kryo.readObject(input, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
