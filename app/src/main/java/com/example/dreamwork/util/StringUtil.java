package com.example.dreamwork.util;

/**
 * Created by co-mall on 2016/4/27.
 */
public class StringUtil {


    /**
     * 截取string中Pid 的内容
     * @param string
     * @return
     */
    public static String Split(String string) {
        String s[] = null;
        String a[] = string.split("pid=");

        if (a[1].contains("&")) {
            s = a[1].split("&");
            return s[0];
        } else {
            return a[1];
        }

    }
}
