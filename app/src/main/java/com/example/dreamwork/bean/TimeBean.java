package com.example.dreamwork.bean;

import java.io.Serializable;

/**
 * Created by engineer on 2016/3/22.
 */
public class TimeBean implements Serializable{
    private String time;
    //状态值，0---已结束，1--正在进行，2--即将开始
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
