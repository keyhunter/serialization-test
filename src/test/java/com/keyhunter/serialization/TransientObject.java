package com.keyhunter.serialization;

import java.io.Serializable;

/**
 * @auther jiujie
 * Created on 2016/12/26.
 */
public class TransientObject implements Serializable {
    private static final long serialVersionUID = 6169749600497460181L;

    private int id;

    private transient String temp;

    @Override
    public String toString() {
        return "{id=" + id + ",temp=" + temp + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
