package com.keyhunter.serialization;

import com.keyhunter.test.serialization.bean.ComplexObject;
import com.keyhunter.test.serialization.bean.ComplexObjectGenerator;
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
 * Created by yingren on 2016/12/2.
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
        SimpleObject simpleObject = buildSimpleObject();
        for (Serializer serializer : serializers) {
            byte[] serialize = serializer.serialize(simpleObject);
            System.out.println(new String(serialize));
            SimpleObject deserialize = serializer.deserialize(serialize, SimpleObject.class);
            Assert.assertEquals(simpleObject.getName(), deserialize.getName());
            Assert.assertEquals(simpleObject.getValue(), deserialize.getValue());
            Assert.assertEquals(simpleObject.getMail(), deserialize.getMail());
            Assert.assertEquals(simpleObject.getAge(), deserialize.getAge());
        }

        ComplexObject complexObject = new ComplexObjectGenerator().generate();
        for (Serializer serializer : serializers) {
            byte[] serialize = serializer.serialize(complexObject);
            ComplexObject deserialize = serializer.deserialize(serialize, ComplexObject.class);
            Assert.assertEquals(complexObject.getJustAName0(), deserialize.getJustAName0());
        }
    }

    public SimpleObject buildSimpleObject() {
        SimpleObject simpleObject = new SimpleObject();
        simpleObject.setName("simple object");
        simpleObject.setValue("i'm a simple object");
        simpleObject.setAge(22);
        simpleObject.setMail("xiaoming@qq.com");
        ArrayList<String> parents = new ArrayList<>();
        parents.add("mother");
        parents.add("father");
        simpleObject.setParents(parents);
        simpleObject.setSchool("Star School");
        simpleObject.setTeacher("James Lee");
        simpleObject.setScore(32.8);
        simpleObject.setHeight(180);
        simpleObject.setWeight(66);
        simpleObject.setDesc("It's my pleasure to introduce my self..well");
        return simpleObject;
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
