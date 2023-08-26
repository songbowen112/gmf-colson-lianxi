package com.colson.dal.dto;

import lombok.Data;

/**
 * @Author: 郭梦丽
 * @Date: 2018/10/30 18:55
 * @Description:
 */
@Data
public class ReqAutoAdaptPaperDTO {
    private Integer knowledgeTreeId; //知识树ID

    private Integer subjectId; //科目ID

    private String name;  //试卷名称

    private String exerciseType;  //试卷类型

    private String endTime; //结束时间

    private Integer questionTopLimit;  //试题数量上限

    private Integer limitType;  //限定类型，勾选状态为1: 限定, 非勾选状态为0: 不限定

    private String limitKnowledgeNodes;  //限定知识点id，字符串逗号隔开

    private String operator;  //操作人

    private String questionType; //试题类型

    private String description; //备注

    private Integer platform; //试卷使用平台：班主任工作台1，讲师0
}
