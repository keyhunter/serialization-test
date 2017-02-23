package com.keyhunter.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * java扩展接口的序列化
 *
 * @auther jiujie
 * Created on 2016/12/24.
 */
public class ExternalInfo implements Externalizable {

    private static final long serialVersionUID = 680172939262235269L;
    private int id;

    private String username;

    private String password;


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(username + "用户");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object o = in.readObject();
        username = (String) o;
    }

    @Override
    public String toString() {
        return "{id=" + id + ",username=" + username + ",password=" + password + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
