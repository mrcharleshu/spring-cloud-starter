package com.charles.springcloud.stream;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

public class Log implements Serializable {

    private int id;
    private String info;

    public Log() {
    }

    public Log(int id, String info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        // return "{\"id\":" + id + ",\"info\":\"" + info + "\"}";
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
