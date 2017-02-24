package com.keyhunter.test.serialization;

/**
 * Created by yingren on 2016/12/2.
 */
public interface Serializer {

    <T> byte[] serialize(T object);

    <T> T deserialize(byte[] bytes, Class<T> clazz);

}
