package com.fantasy.Util;

/**
 * Created by co-mall on 2016/6/15.
 */
public class RunnableDemo implements Runnable {
    private int ticket =10;
    private String name;
    @Override
    public void run() {
        for(int i =0;i<500;i++){
            if(this.ticket>0){
                System.out.println(Thread.currentThread().getName()+"³öÆ±---->"+(this.ticket--));
            }
        }
    }
}
