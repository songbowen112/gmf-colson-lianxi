package com.colson.util;

import java.text.DecimalFormat;

public class MathUtils {

    /**
     * TODO 除法运算，保留两位小数
     * @param a 被除数
     * @param b 除数
     * @return 商
     */
    public static String txFloat(int a,int b) {
        // TODO 自动生成的方法存根

        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数

        return df.format((float)a/b);

    }
}
