package com.healthy.androidit.mewkybar.healthyapp.Sleep;

import java.io.Serializable;

public class Sleep implements Serializable {
    private int id;
    private String date;
    private String toBedTime;
    private String awakeTime;
    private String sleepTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getToBedTime() {
        return toBedTime;
    }

    public void setToBedTime(String toBedTime) {
        this.toBedTime = toBedTime;
    }

    public String getAwakeTime() {
        return awakeTime;
    }

    public void setAwakeTime(String awakeTime) {
        this.awakeTime = awakeTime;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }
}
