package com.keyhunter.serialization;

import com.keyhunter.test.serialization.Serializer;
import com.keyhunter.test.serialization.jdk.JdkSerializer;
import com.keyhunter.test.serialization.json.FastJSONSerializer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jiujie on 2016/12/2.
 */
public class ExternalSerializeTest {

    @Test
    public void externalTest() {
        ExternalInfo externalInfo = new ExternalInfo();
        externalInfo.setId(1);
        externalInfo.setPassword("xxxxx");
        externalInfo.setUsername("小明");
        //jdk
        Serializer jdkSerializer = new JdkSerializer();
        byte[] serialize = jdkSerializer.serialize(externalInfo);
        ExternalInfo deExternalInfo = jdkSerializer.deserialize(serialize, ExternalInfo.class);
        Assert.assertNotEquals(externalInfo.getId(), deExternalInfo.getId());
        Assert.assertNotEquals(externalInfo.getPassword(), deExternalInfo.getPassword());
        Assert.assertEquals(externalInfo.getUsername()+"用户", deExternalInfo.getUsername());
        //json
        Serializer jsonSerializer = new FastJSONSerializer();
        serialize = jsonSerializer.serialize(externalInfo);
        deExternalInfo = jsonSerializer.deserialize(serialize, ExternalInfo.class);
        Assert.assertEquals(externalInfo.getId(), deExternalInfo.getId());
        Assert.assertEquals(externalInfo.getPassword(), deExternalInfo.getPassword());
        Assert.assertEquals(externalInfo.getUsername(), deExternalInfo.getUsername());
        //protobuffer
        Serializer protoBufferSerializer = new FastJSONSerializer();
        serialize = protoBufferSerializer.serialize(externalInfo);
        deExternalInfo = protoBufferSerializer.deserialize(serialize, ExternalInfo.class);
        Assert.assertEquals(externalInfo.getId(), deExternalInfo.getId());
        Assert.assertEquals(externalInfo.getPassword(), deExternalInfo.getPassword());
        Assert.assertEquals(externalInfo.getUsername(), deExternalInfo.getUsername());
    }

}
