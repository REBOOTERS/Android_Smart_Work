package com.fantasy.Util;

/**
 * Created by co-mall on 2016/6/15.
 */
public class ThreadTest {
    public static void main(String[] args) {
        ThreadDemo mt1 = new ThreadDemo("����1");
        ThreadDemo mt2 = new ThreadDemo("����2");
        ThreadDemo mt3 = new ThreadDemo("����3");
//        mt1.start();
//        mt2.start();
//        mt3.start();


        RunnableDemo mt = new RunnableDemo();
        Thread t1 = new Thread(mt, "����1");
        Thread t2 = new Thread(mt, "����2");
        Thread t3 = new Thread(mt, "����3");
        t1.start();
        t2.start();
        t3.start();
    }
}
