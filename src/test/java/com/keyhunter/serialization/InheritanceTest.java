package com.keyhunter.serialization;

import com.keyhunter.serialization.inheritance.SubClass;
import com.keyhunter.test.serialization.Serializer;
import com.keyhunter.test.serialization.jdk.JdkSerializer;
import com.keyhunter.test.serialization.json.FastJSONSerializer;
import com.keyhunter.test.serialization.protobuffer.ProtoBufferSerializer;
import org.junit.Assert;
import org.junit.Test;

/**
 * @auther yingren
 * Created on 2016/12/26.
 */
public class InheritanceTest {

    @Test
    public void test() {
        SubClass subClass = new SubClass();
        subClass.setSuperName("父类");
        subClass.setSubName("子类");
        //jdk
        Serializer jdkSerializer = new JdkSerializer();
        byte[] serialize = jdkSerializer.serialize(subClass);
        SubClass deserialize = jdkSerializer.deserialize(serialize, SubClass.class);
        Assert.assertNull(deserialize.getSuperName());
        Assert.assertNotNull(deserialize.getSubName());
        //json
        Serializer jsonSerializer = new FastJSONSerializer();
        serialize = jsonSerializer.serialize(subClass);
        deserialize = jsonSerializer.deserialize(serialize, SubClass.class);
        Assert.assertNotNull(deserialize.getSuperName());
        Assert.assertNotNull(deserialize.getSubName());
        //proto
        Serializer protoBufferSerializer = new ProtoBufferSerializer();
        serialize = protoBufferSerializer.serialize(subClass);
        deserialize = protoBufferSerializer.deserialize(serialize, SubClass.class);
        Assert.assertNotNull(deserialize.getSuperName());
        Assert.assertNotNull(deserialize.getSubName());

    }
}
