package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/9.
 */
@Data
public class ReqPaperQuestionDTO {
    private Integer id;
    private String code;
    private Integer sequence;
    private BigDecimal score;
    private Integer difficultyValue;
    private Integer parentQuestionId = 0;

    private Integer paperId;
    private Date createTime;
    private Date updateTime;
    private String operator;
    private Integer deleteFlag;
    private String questionType;  // 新增试题类型，用于试题分组
    private String sourceType; // 试题来源，用于区分真题和非真题

    public ReqPaperQuestionDTO() {
    }

    public ReqPaperQuestionDTO(Integer id, String code, Integer sequence, BigDecimal score, Integer difficultyValue) {
        this.id = id;
        this.code = code;
        this.sequence = sequence;
        this.score = score;
        this.difficultyValue = difficultyValue;
    }
}
