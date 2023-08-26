package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hurw on 2017/8/14.
 */
@Data
public class QuestionMain {
    private Integer id;
    private String code;
    private String sourceType;
    private String questionType;
    private String contentType;
    private Integer questionId;
    private BigDecimal score;
    private Integer knowledgeTreeId;
    private Integer mainNodeId;
    private Integer viceNodeId1;
    private Integer viceNodeId2;
    private Integer difficultyValue;
    private Integer examProvince;
    private Integer examSession;
    private Integer examTag;
    private String examSessionAndProvinces;
    private Integer currentVersion;
    private Integer parentQuestionId;
    private Integer sequence;
    private Integer invalidFlag;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String operator;
    private Integer deleteFlag;

}
