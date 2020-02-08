package com.Entity.Impl;

import java.io.Serializable;

/**
 * Create by Joyyue sheting on 2020/2/6
 */
public class HunterImpl implements Serializable {

    private int hunterID;

    private String hunterName;

    public int getHunterID() {
        return hunterID;
    }

    public void setHunterID(int hunterID) {
        this.hunterID = hunterID;
    }

    public String getHunterName() {
        return hunterName;
    }

    public void setHunterName(String hunterName) {
        this.hunterName = hunterName;
    }

    @Override
    public String toString() {
        return "Hunter" + "[id=" + this.hunterID + ",name=" + this.hunterName + "]";
    }

}
