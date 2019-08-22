package com.colson.dal.util;

import java.util.Arrays;

public class ArraysUtils {

    public static String printArr(Object[] ints) {
        int index;
        //打印一维数组
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (null != ints) {
            Arrays.stream(ints).forEach(i -> {
                sb.append(i + " ");
            });
            index = sb.lastIndexOf(" ");
            sb.deleteCharAt(index);
        }

        sb.append("]\n");
        return sb.toString();
    }

    public static String printArr2(Object[] ints) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (null != ints) {
            for (int i = 0; i < ints.length; i++) {
                if(i==ints.length-1) {
                    sb.append(ints[i]);
                } else {
                    sb.append(ints[i] + " ");
                }
            }
        }
        sb.append("]\n");
        return sb.toString();
    }


}
