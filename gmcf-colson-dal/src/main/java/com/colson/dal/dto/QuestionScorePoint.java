package com.colson.dal.dto;

import lombok.Data;

import java.util.Date;

/***
 * @Description:AI判分标准
 * @author suntenghao
 * @date 2018-09-10 11:14
 */
@Data
public class QuestionScorePoint {
    private Integer id;
    private Integer questionId;
    private Integer questionParentId;
    private String scorePointGroup;
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String operator;
    private Integer deleteFlag;
}
