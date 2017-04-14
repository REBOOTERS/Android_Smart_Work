package com.fantasy.Util;

/**
 * Created by co-mall on 2016/6/15.
 */
public class ThreadDemo extends Thread {

    private int ticket = 10;
    private String name;






    public ThreadDemo(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            if (this.ticket > 0) {
                System.out.println(this.name + "³öÆ±---->" + (this.ticket--));
            }
        }
    }


}
