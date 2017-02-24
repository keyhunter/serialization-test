package com.keyhunter.test.serialization;

import java.io.Serializable;

/**
 * @auther yingren
 * Created on 2017/2/23.
 */
public class StatisticsCollecter {

    private int loopSize;


    public StatisticsCollecter(Config config) {
        this.loopSize = config.getLoopSize();
    }


    /**
     * Collect statistics.
     *
     * @param serializer
     * @param serializable
     * @return
     */
    public Statistics collect(Serializer serializer, Serializable serializable) {

        String simpleName = serializer.getClass().getSimpleName();
        Statistics statistics = new Statistics(simpleName.replace("Serializer", ""));

        long totalSerializeCostTime = 0L;
        long totalDeserializeCostTime = 0L;
        byte[] preSerialize = serializer.serialize(serializable);
        statistics.setSize(preSerialize.length);
        for (int i = 0; i < loopSize; i++) {
            long startTime = System.nanoTime();
            byte[] serialize = serializer.serialize(serializable);
            long endTime1 = System.nanoTime();
            serializer.deserialize(serialize, serializable.getClass());
            long endTime2 = System.nanoTime();
            long serializeCostTime = endTime1 - startTime;
            totalSerializeCostTime += serializeCostTime;
            setSerializeCostTime(statistics, serializeCostTime);
            long deserializeCostTime = endTime2 - endTime1;
            totalDeserializeCostTime += deserializeCostTime;
            setDeserialeCostTime(statistics, deserializeCostTime);
        }
        statistics.setAvgSerializeCostTime(totalSerializeCostTime / loopSize);
        statistics.setAvgDeserializeCostTime(totalDeserializeCostTime / loopSize);
        return statistics;
    }

    /**
     * set min and max deserializeCostTime
     *
     * @param statistics
     * @param deserializeCostTime
     */
    private void setDeserialeCostTime(Statistics statistics, long deserializeCostTime) {
        if (statistics.getMinDeserializeCostTime() == 0 || statistics.getMinDeserializeCostTime() > deserializeCostTime) {
            statistics.setMinDeserializeCostTime(deserializeCostTime);
        }
        if (statistics.getMaxDeserializeCostTime() == 0 || statistics.getMaxDeserializeCostTime() < deserializeCostTime) {
            statistics.setMaxDeserializeCostTime(deserializeCostTime);
        }
    }

    /**
     * set min and max serializeCostTime
     *
     * @param statistics
     * @param serializeCostTime
     */
    private void setSerializeCostTime(Statistics statistics, long serializeCostTime) {
        if (statistics.getMinSerializeCostTime() == 0 || statistics.getMinSerializeCostTime() > serializeCostTime) {
            statistics.setMinSerializeCostTime(serializeCostTime);
        }
        if (statistics.getMaxSerializeCostTime() == 0 || statistics.getMaxSerializeCostTime() < serializeCostTime) {
            statistics.setMaxSerializeCostTime(serializeCostTime);
        }
    }
}