package com.colson.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义转换日期格式的方法，controller的返回值转换为json格式会用到此方法
 * Created by gaolei on 2017/7/14.
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws
            IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(value);

        gen.writeString(formattedDate);
    }
}
