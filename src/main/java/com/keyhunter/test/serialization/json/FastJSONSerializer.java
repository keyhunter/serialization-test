package com.keyhunter.test.serialization.json;

import com.alibaba.fastjson.JSON;
import com.keyhunter.test.serialization.Serializer;

/**
 * JSON序列化方式
 * 使用FastJson框架
 * 支持不同风格的序列化方式：驼峰，蛇形
 * 支持复杂类型的序列化,比如循环引用，ps:最好不要用，提升复杂度
 * @auther jiujie
 * Created on 2016/12/21.
 */
public class FastJSONSerializer implements Serializer {
    @Override
    public <T> byte[] serialize(T object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
