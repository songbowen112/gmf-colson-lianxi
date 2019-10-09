package com.colson.dal.util;

import com.colson.dal.bean.Condition;
import com.colson.dal.util.emum.JudgeType;

import java.util.*;

public class BeanUtils {

    public static Map<String, Object> convertRequest(List<Condition> beans) {
        Map<String, Object> conditions = new HashMap<>();

        if (null == beans || beans.isEmpty()) {
            return conditions;
        }

        StringBuilder sb = new StringBuilder();
        beans.stream().forEach(i -> {
            Condition condition = i.analysis();
            if (JudgeType.IN.equals(condition.getJudge())) {
                String[] values = i.getOrs().split(".");

                sb.append(field + " in\n (");
                for (String value : values) {
                    sb.append(value + ",");
                }
                int index = sb.lastIndexOf(",");
                sb.deleteCharAt(index);
                sb.append(") ");
            }
            if (JudgeType.EQ.equals(fullName[1])) {

            }

            if (JudgeType.BETWEEN.equals(fullName[1])) {

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
