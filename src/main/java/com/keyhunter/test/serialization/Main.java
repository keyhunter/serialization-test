package com.keyhunter.test.serialization;

import com.keyhunter.test.serialization.bean.SimpleObject;
import com.keyhunter.test.serialization.jdk.JdkSerializer;
import com.keyhunter.test.serialization.json.FastJSONSerializer;
import com.keyhunter.test.serialization.json.JackJSONSerializer;
import com.keyhunter.test.serialization.kryo.KryoSerializer;
import com.keyhunter.test.serialization.protobuffer.ProtoBufferSerializer;
import com.keyhunter.test.serialization.xml.XMLSerializer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Application entry point
 *
 * @auther jiujie
 * Created on 2017/2/23.
 */
public class Main {

    private static List<Serializer> serializers = new ArrayList<>();


    public static void register(Serializer serializer) {
        serializers.add(serializer);
    }

    public static void main(String[] args) {
        register(new JdkSerializer());
        register(new FastJSONSerializer());
        register(new ProtoBufferSerializer());
        register(new XMLSerializer());
        register(new JackJSONSerializer());
        register(new KryoSerializer());
        List<Statistics> statisticsList = statistics();
        Collections.sort(statisticsList, new Comparator<Statistics>() {
            @Override
            public int compare(Statistics o1, Statistics o2) {
                if (o1.getAvgSerializeCostTime() > o2.getAvgSerializeCostTime()) {
                    return 1;
                } else if (o1.getAvgSerializeCostTime() == o2.getAvgSerializeCostTime()) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        print(statisticsList);
    }

    private static void print(List<Statistics> statisticsList) {
        System.out.println(Statistics.header());
        for (Statistics statistics : statisticsList) {
            System.out.println(statistics.toString());
        }
    }

    private static List<Statistics> statistics() {
        final List<Statistics> statisticsList = new ArrayList<>();
        Config config = new Config();
        config.setLoopSize(10000);
        final StatisticsCollecter runner = new StatisticsCollecter(config);
        final CountDownLatch countDownLatch = new CountDownLatch(serializers.size());
        final SimpleObject simpleObject = new SimpleObject("testSimple", "It's a simple object", 30, "xxxxx@qq.com");
        for (final Serializer serializer : serializers) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Statistics statistics = runner.collect(serializer, simpleObject);
                    statistics.setName(statistics.getName() + "-Simple");
                    statisticsList.add(statistics);
                    countDownLatch.countDown();
                }
            });
            thread.start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return statisticsList;
    }
}
