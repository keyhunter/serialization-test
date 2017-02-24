package com.keyhunter.test.serialization;

import com.keyhunter.test.serialization.bean.BigObject;
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
public class Application {

    private List<Serializer> serializers = new ArrayList<>();

    private List<Statistics> statisticsList;
    private final Config config;

    private StatisticsCollecter statisticsCollecter;


    public static void main(String[] args) {
        Config config = new Config();
        config.setLoopSize(12);
        config.setNameSuffix("Simple");
        config.setTargetObject(buildSimpleObject());
        Application application = new Application(config);
        application.init();
        application.run();
//        config.setLoopSize(10000);
        config.setNameSuffix("Complex");
        config.setTargetObject(new BigObject());
//        application.run();
    }

    public static SimpleObject buildSimpleObject() {
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

    private void run() {
        statistics();
        print();
    }

    public Application(Config config) {
        this.config = config;
    }

    public void init() {
        register(new JdkSerializer());
        register(new FastJSONSerializer());
        register(new ProtoBufferSerializer());
        register(new XMLSerializer());
        register(new JackJSONSerializer());
        register(new KryoSerializer());
        statisticsCollecter = new StatisticsCollecter(config);
    }

    public void register(Serializer serializer) {
        serializers.add(serializer);
    }

    public void print() {
        sortStatistics();
        System.out.println("\n--------------------------------------------------------------------------------------------");
        System.out.println(Statistics.header());
        for (Statistics statistics : statisticsList) {
            System.out.println(statistics.toString());
        }
    }

    private void sortStatistics() {
        Collections.sort(statisticsList, new Comparator<Statistics>() {
            @Override
            public int compare(Statistics o1, Statistics o2) {
                long avgCostTime1 = o1.getAvgSerializeCostTime() + o1.getAvgDeserializeCostTime();
                long avgCostTime2 = o2.getAvgSerializeCostTime() + o2.getAvgDeserializeCostTime();
                if (avgCostTime1 > avgCostTime2) {
                    return 1;
                } else if (avgCostTime1 == avgCostTime2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
    }

    public void statistics() {
        final List<Statistics> statisticsList = new ArrayList<>();

        final CountDownLatch countDownLatch = new CountDownLatch(serializers.size());
        for (final Serializer serializer : serializers) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Statistics statistics = statisticsCollecter.collect(serializer, config.getTargetObject());
                    statistics.setName(statistics.getName() + "-" + config.getNameSuffix());
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
        this.statisticsList = statisticsList;
    }
}
