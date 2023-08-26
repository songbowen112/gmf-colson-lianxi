package com.colson.dal.dto;

import lombok.Data;

/**
 * @Author: 郭梦丽
 * @Date: 2018/10/31 10:59
 * @Description:
 */
@Data
public class ReqAutoAdaptPaperConditionDTO {

    private Integer knowledgeTreeId; //知识树ID

    private String exerciseType;  //试卷类型

    private String operator;    //操作人

    private Integer invalidFlag;  //状态值。1: 禁用, 0: 启用

    private Integer pageSize;  //每页条目数

    private Integer pageNo;  //当前页码

    private Integer pageIndex;
}
