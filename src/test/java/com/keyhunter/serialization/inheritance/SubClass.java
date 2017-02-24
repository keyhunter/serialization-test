package com.keyhunter.serialization.inheritance;

import java.io.Serializable;

/**
 * @auther yingren
 * Created on 2016/12/26.
 */
public class SubClass extends SuperClass implements Serializable{

    private String subName;

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Override
    public String toString() {
        return "{superName=" + getSuperName() + ",subName=" + subName + "}";
    }
}
