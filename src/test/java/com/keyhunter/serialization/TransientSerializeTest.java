package com.keyhunter.serialization;

import com.keyhunter.test.serialization.Serializer;
import com.keyhunter.test.serialization.jdk.JdkSerializer;
import com.keyhunter.test.serialization.json.FastJSONSerializer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jiujie on 2016/12/2.
 */
public class TransientSerializeTest {

    @Test
    public void transientTest() {
        TransientObject transientObject = new TransientObject();
        transientObject.setId(1);
        transientObject.setTemp("temp data");
        //jdk
        Serializer jdkSerializer = new JdkSerializer();
        byte[] serialize = jdkSerializer.serialize(transientObject);
        TransientObject deTransientObjcet = jdkSerializer.deserialize(serialize, TransientObject.class);
        Assert.assertNull(deTransientObjcet.getTemp());
        Assert.assertTrue(deTransientObjcet.getId() == 1);
        //json
        Serializer jsonSerializer = new FastJSONSerializer();
        serialize = jsonSerializer.serialize(transientObject);
        deTransientObjcet = jsonSerializer.deserialize(serialize, TransientObject.class);
        Assert.assertNull(deTransientObjcet.getTemp());
        Assert.assertTrue(deTransientObjcet.getId() == 1);
        //protobuffer
        Serializer protoBufferSerializer = new FastJSONSerializer();
        serialize = protoBufferSerializer.serialize(transientObject);
        deTransientObjcet = protoBufferSerializer.deserialize(serialize, TransientObject.class);
        Assert.assertNull(deTransientObjcet.getTemp());
        Assert.assertTrue(deTransientObjcet.getId() == 1);

    }


}
