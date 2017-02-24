package com.keyhunter.test.serialization;

import java.io.Serializable;

/**
 * The config for serialize test.
 *
 * @auther jiujie
 * Created on 2017/2/23.
 */
public class Config {

    /**
     * statistics loop size
     */
    private int loopSize;

    /**
     * target obj to serialize and deserialize;
     */
    private Serializable targetObject;

    /**
     * statistics name suffix
     */
    private String nameSuffix;


    public int getLoopSize() {
        return loopSize;
    }

    public void setLoopSize(int loopSize) {
        this.loopSize = loopSize;
    }

    public Serializable getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Serializable targetObject) {
        this.targetObject = targetObject;
    }

    public String getNameSuffix() {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix) {
        this.nameSuffix = nameSuffix;
    }
}
