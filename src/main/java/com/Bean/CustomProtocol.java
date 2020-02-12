package com.Bean;

import java.io.Serializable;

/**
 * Create by Joyyue sheting on 2020/2/12
 */
public class CustomProtocol implements Serializable  {

    private static final long serialVersionUID = 4671171056588401542L;
    private long id;
    private String content;
    //省略 getter/setter

    public String getContent() {
        return content;
    }

    public long getId() {
        return id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
