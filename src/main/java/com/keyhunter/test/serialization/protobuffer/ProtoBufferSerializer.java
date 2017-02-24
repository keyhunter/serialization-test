package com.keyhunter.test.serialization.protobuffer;

import com.keyhunter.test.serialization.Serializer;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.DefaultIdStrategy;
import io.protostuff.runtime.RuntimeEnv;
import io.protostuff.runtime.RuntimeSchema;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ProtoBuffer序列化
 * 用的是ProtoStuff工具包
 * @auther yingren
 * Created on 2016/12/21.
 */
public class ProtoBufferSerializer implements Serializer {

    private final static DefaultIdStrategy idStrategy = ((DefaultIdStrategy) RuntimeEnv.ID_STRATEGY);

    /**
     * 缓存Schema防止每次解析Schema带来的开销
     */
    private final static ConcurrentHashMap<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    @Override
    public <T> byte[] serialize(T object) {
        if (object == null) {
            return null;
        }
        @SuppressWarnings("unchecked")
        Class<T> cls = (Class<T>) object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            byte[] bytes = ProtostuffIOUtil.toByteArray(object, schema, buffer);
            return bytes;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            Schema<T> schema = getSchema(clazz);
            //改为由Schema来实例化解码对象，没有构造函数也没有问题
            T message = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, message, schema);
            return message;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz, idStrategy);
            cachedSchema.put(clazz, schema);
        }
        return schema;
    }
}
