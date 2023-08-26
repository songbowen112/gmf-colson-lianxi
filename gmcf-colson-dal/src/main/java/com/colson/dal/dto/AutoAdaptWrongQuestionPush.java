package com.colson.dal.dto;

import lombok.Data;

/**
 * @author: maxiao
 * @description:
 * @date: create in 9:58 2018/11/6
 * @modified By:
 * @copyright by sunlands
 */
@Data
public class AutoAdaptWrongQuestionPush {

    private Integer id;
    private String exerciseType;
    private String questionType;
    private String name;
    private String endTime;
    private Integer questionTopLimit;
    private Integer invalidFlag;
    private Integer knowledgeTreeId;
    private Integer subjectId;
    private Integer limitType;
    private String limitKnowledgeNodes;
    private Integer participateNumber;
    private Integer createTime;
    private Integer updateTime;
    private String operator;
    private Integer deleteFlag;

}
