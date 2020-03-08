package com.colson.dal.util;

import com.colson.dal.bean.Condition;
import com.colson.dal.util.emum.JudgeType;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class BeanUtils {

    public static void main(String[] args) {
        Condition c = new Condition();
        c.setCondition("f_bidding_no.eq");
        c.setValue("GMBEC20020511470202500045");

        Condition c2 = new Condition();
        c2.setCondition("f_status.in");
        c2.setValues(new Object[]{1,2,3,4,5});

        Condition c3 = new Condition();
        c3.setCondition("f_type.notin");
        c3.setValues(new Object[]{1,2,3});

        Condition c4 = new Condition();
        c4.setCondition("f_create_time.between");
        c4.setValues(new String[]{"2020-03-08 00:00:00","2020-12-01 23:59:59"});

        List<Condition> ands = new ArrayList<>();
        ands.add(c2);
        ands.add(c3);
        ands.add(c4);
        c.setAnds(ands);

        System.out.println(convertRequest(c,new String()));
    }

    public static String convertRequest(Condition param,String str) {
        if (null == param) {
            return "";
        }

        StringBuilder sb = new StringBuilder(str);
        Condition condition = param.analysis();

        String field = param.getName();
        Object[] values = param.getValues();
        Object value = param.getValue();

        if (JudgeType.IN.equals(condition.getJudge())) {

            sb.append(field + " in\n (");
            for (Object obj : values) {
                sb.append(obj + ",");
            }
            int index = sb.lastIndexOf(",");
            sb.deleteCharAt(index);
            sb.append(")");
        }
        if (JudgeType.NOTIN.equals(condition.getJudge())) {

            sb.append(field + " not in\n (");
            for (Object obj : values) {
                sb.append(obj + ",");
            }
            int index = sb.lastIndexOf(",");
            sb.deleteCharAt(index);
            sb.append(")");
        }
        if (JudgeType.EQ.equals(condition.getJudge())) {
            sb.append(field + "=" + value);
        }
        if (JudgeType.BETWEEN.equals(condition.getJudge())) {
            sb.append(field + " between "+ values[0] + " and " + values[1]);
        }
        if (!CollectionUtils.isEmpty(param.getAnds())) {
            sb.append(" and ");
            param.getAnds().forEach(i -> {
                convertRequest(i,sb.toString());
            });
        }
        if (!CollectionUtils.isEmpty(param.getOrs())) {
            sb.append(" or ");
            param.getOrs().forEach(i -> {
                convertRequest(i,sb.toString());
            });
        }
        return sb.toString();
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
