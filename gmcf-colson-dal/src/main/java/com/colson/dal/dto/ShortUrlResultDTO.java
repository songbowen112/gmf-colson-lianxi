package com.colson.dal.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: hongbo.wang
 * @Date: 2020/12/18
 * @Description:短链服务返回vo
 */
@Data
public class ShortUrlResultDTO implements Serializable {

    /**
     * 200：成功；400：非法参数；401：短域名生成错误；404：未找到短域名；500：程序异常
     */
    private Integer code;
    private String message;
    private String shortUrl;
    private String longUrl;

}
