package com.keyhunter.test.serialization.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyhunter.test.serialization.Serializer;

import java.io.IOException;

/**
 * @auther yingren
 * Created on 2017/2/23.
 */
public class JackJSONSerializer implements Serializer {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T object) {
        try {
            return mapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try {
            return mapper.readValue(bytes, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
