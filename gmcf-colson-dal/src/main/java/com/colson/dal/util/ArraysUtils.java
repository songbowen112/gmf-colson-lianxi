package com.colson.dal.util;

import java.util.Arrays;

public class ArraysUtils {

    public static String printArr(Object[] array) {
        int index;
        //打印一维数组
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (null != array) {
            Arrays.stream(array).forEach(i -> {
                sb.append(i + " ");
            });
            index = sb.lastIndexOf(" ");
            sb.deleteCharAt(index);
        }

        sb.append("]\n");
        return sb.toString();
    }

    public static String printArr2(Object[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if(i==array.length-1) {
                    sb.append(array[i]);
                } else {
                    sb.append(array[i] + " ");
                }
            }
        }
        sb.append("]\n");
        return sb.toString();
    }


}
