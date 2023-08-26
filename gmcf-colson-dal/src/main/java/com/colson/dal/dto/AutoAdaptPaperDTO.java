package com.colson.dal.dto;

import lombok.Data;

/**
 * @Author: 郭梦丽
 * @Date: 2018/10/31 11:01
 * @Description:
 */

@Data
public class AutoAdaptPaperDTO {

    private Integer id;  //试卷id

    private String name;  //试卷名称

    private String exerciseType;  //试卷类型

    private String endTime; //结束时间

    private String effectiveStatus;  //时效，中文"有效/失效"

    private Integer questionTopLimit;  //试题数量上限

    private Integer participateNumber = 0;  //参与人数

    private Integer invalidFlag;  //状态值。1: 禁用, 0: 启用

    private String mLinkUrl;  //微信链接，或者用来内部跳转的URL
}
