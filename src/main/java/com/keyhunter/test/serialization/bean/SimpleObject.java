package com.keyhunter.test.serialization.bean;

import java.io.Serializable;

/**
 * Created by jiujie on 2016/12/2.
 */
public class SimpleObject implements Serializable {

    private static final long serialVersionUID = 8991617066265075347L;

    private String name;

    private String value;

    private int age;

    private String mail;

    public SimpleObject() {
    }

    public SimpleObject(String name, String value, int age, String mail) {
        this.name = name;
        this.value = value;
        this.age = age;
        this.mail = mail;
    }

    public SimpleObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
