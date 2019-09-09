package com.colson.dal.util;

import com.colson.dal.bean.CommonBean;
import com.colson.dal.util.constant.OperationConstant;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class BeanUtils {

    public static Map<String, String> convertRequest(List<CommonBean> beans) {
        Map<String, String> valueMap = new HashMap<>();
        Map<String, String> typeMap = new HashMap<>();

        if (null == beans || beans.isEmpty()) {
            return valueMap;
        }

        StringBuilder sb = new StringBuilder();
        beans.stream().forEach(i -> {
            String[] fullName = i.getParamName().split(".");
            String field = convertField(fullName[0]);
            if (OperationConstant.IN.equals(fullName[1])) {
                String[] values = i.getParamValue().split(".");

                sb.append(field + " in\n (");
                for (String value : values) {
                    sb.append(value + ",");
                }
                int index = sb.lastIndexOf(",");
                sb.deleteCharAt(index);
                sb.append(") ");
            }
            if (OperationConstant.QUERY.equals(fullName[1])) {

            }

            if (OperationConstant.BETWEEN.equals(fullName[1])) {

            }


            valueMap.put(fullName[0], i.getParamValue());
            typeMap.put(fullName[0], fullName[1]);
        });
        return null;
    }

    private static String convertField(String fieldName) {
        StringBuffer sb = new StringBuffer();
        int i;
        for (i = 0; i <= fieldName.length() - 1; i++) {//遍历字符串  
            char ch = 0;
            //通过fieldName.charAt(i)遍历出字符串中每个字符  
            if (fieldName.charAt(i) >= 'A' && fieldName.charAt(i) <= 'Z') {//判断字符是否在a-z之间（小写） 
                ch = (char) (fieldName.charAt(i) - 32);//如果为小写则转换为相应大写,赋值给ch
                sb.append("_");
            }
            sb.append(ch);//将字符追加到sb序列   
        }
        return sb.toString();
    }


}
