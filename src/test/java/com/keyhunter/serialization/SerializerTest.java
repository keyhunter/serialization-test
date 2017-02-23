package com.keyhunter.serialization;

import com.keyhunter.test.serialization.jdk.JdkSerializer;
import com.keyhunter.test.serialization.Serializer;
import com.keyhunter.test.serialization.bean.SimpleObject;
import com.keyhunter.test.serialization.json.FastJSONSerializer;
import com.keyhunter.test.serialization.json.JackJSONSerializer;
import com.keyhunter.test.serialization.kryo.KryoSerializer;
import com.keyhunter.test.serialization.protobuffer.ProtoBufferSerializer;
import com.keyhunter.test.serialization.xml.XMLSerializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test all serializers.
 * Created by jiujie on 2016/12/2.
 */
public class SerializerTest {

    @Test
    public void test() {
        List<Serializer> serializers = new ArrayList<>();
        serializers.add(new KryoSerializer());
        serializers.add(new JdkSerializer());
        serializers.add(new FastJSONSerializer());
        serializers.add(new JackJSONSerializer());
        serializers.add(new ProtoBufferSerializer());
        serializers.add(new XMLSerializer());
        SimpleObject simpleObject = new SimpleObject("小明");
        simpleObject.setValue("xxxx");
        simpleObject.setAge(18);
        simpleObject.setMail("jiujie@xx.com");
        for (Serializer serializer : serializers) {
            byte[] serialize = serializer.serialize(simpleObject);
            SimpleObject deserialize = serializer.deserialize(serialize, SimpleObject.class);
            Assert.assertEquals(simpleObject.getName(), deserialize.getName());
            Assert.assertEquals(simpleObject.getValue(), deserialize.getValue());
            Assert.assertEquals(simpleObject.getMail(), deserialize.getMail());
            Assert.assertEquals(simpleObject.getAge(), deserialize.getAge());
        }

    }

//    @Test
//    public void test() {
//        SimpleObject simpleObject = new SimpleObject("小明");
//        Serializer jdkSerializer = new JdkSerializer();
//        byte[] serialize = jdkSerializer.serialize(simpleObject);
//        File file = new File("E:/SimpleObject.obj");
//        try (FileOutputStream outputStream = new FileOutputStream(file);) {
//            outputStream.write(serialize);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try (FileInputStream in = new FileInputStream(file);) {
//            ObjectInputStream oin = new ObjectInputStream(in);
//            Object o = oin.readObject();
//            System.out.println(o);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        SimpleObject simpleObject1 = jdkSerializer.deserialize(serialize, SimpleObject.class);
//        System.out.println(simpleObject1.getName());
//
//    }
}
