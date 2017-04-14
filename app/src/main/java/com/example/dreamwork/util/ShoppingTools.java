package com.example.dreamwork.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by co-mall on 2016/6/14.
 */
public class ShoppingTools {
    /**
     * 截取小数点后两位，而不是四舍五入
     *
     * @param number
     * @return
     */
    public static String FormatNum2(String number) {
        String intNumber;
        if (number.contains(".")) {
            int dot_index = number.indexOf(".");

            System.err.println("the dot index is " + dot_index);

            try {
                intNumber = number.substring(0, dot_index + 3);
            } catch (Exception e) {
                intNumber = number + "0";

            }
            System.out.println(intNumber);
        } else {
            System.out.println(number);
            intNumber = number + ".00";

        }
        return intNumber;
    }

    /**
     * 小数点后保留两位
     */
    public static String FormatNum2(double f) {
        BigDecimal bd = new BigDecimal(f);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.err.println("------------" + bd);
        return bd.toString();

    }

    /**
     * 判断电话号码是否有效
     * 移动：134、135、136、137、138、139、147、150、151、152、157、158、159、182、183、187、188
     * 联通：130、131、132、145、155、156、185、186
     * 电信：133、153、180、181、189
     * 虚拟运营商：17x
     */
    public static boolean isMobileNO(String number) {
        if (number.startsWith("+86")) {
            number = number.substring(3);
        }

        if (number.startsWith("+") || number.startsWith("0")) {
            number = number.substring(1);
        }

        number = number.replace(" ", "").replace("-", "");
        System.out.print("号码：" + number);

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-3,5-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(number);

        return m.matches();
    }
}
