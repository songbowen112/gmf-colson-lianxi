package com.colson.util;

import com.colson.common.bean.Condition;
import com.colson.common.emum.JudgeType;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

    private static StringBuilder builder = new StringBuilder();

    public static String convertRequest(Condition param,String str) {
        if (null == param) {
            return "";
        }
        Condition condition = param.analysis();

        String field = param.getName();
        Object[] values = param.getValues();
        Object value = param.getValue();

        if (JudgeType.IN.equals(condition.getJudge())) {
            builder.append(" and ");

            builder.append(field + " in\n (");
            for (Object obj : values) {
                builder.append(obj + ",");
            }
            int index = builder.lastIndexOf(",");
            builder.deleteCharAt(index);
            builder.append(")");
        }
        if (JudgeType.NOTIN.equals(condition.getJudge())) {
            builder.append(" and ");

            builder.append(field + " not in\n (");
            for (Object obj : values) {
                builder.append(obj + ",");
            }
            int index = builder.lastIndexOf(",");
            builder.deleteCharAt(index);
            builder.append(")");
        }
        if (JudgeType.EQ.equals(condition.getJudge())) {
            builder.append(" and ");

            builder.append(field + "=" + value);
        }
        if (JudgeType.BETWEEN.equals(condition.getJudge())) {
            builder.append(" and ");

            builder.append(field + " between "+ values[0] + " and " + values[1]);
        }
        if (!CollectionUtils.isEmpty(param.getAnds())) {
            param.getAnds().forEach(i -> {
                convertRequest(i,builder.toString());
            });
        }
        if (!CollectionUtils.isEmpty(param.getOrs())) {
            param.getOrs().forEach(i -> {
                convertRequest(i,builder.toString());
            });
        }
        return builder.toString();
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
