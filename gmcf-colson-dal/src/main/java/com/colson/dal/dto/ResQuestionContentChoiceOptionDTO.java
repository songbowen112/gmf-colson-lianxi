package com.colson.dal.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hurw on 2017/8/22.
 */
@Data
public class ResQuestionContentChoiceOptionDTO implements Serializable {
    private Integer id;
    private Integer questionId;
    private Integer sequence;
    private String optionTitle;
    private String content;
    private Integer isCorrect;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String operator;
    private Integer deleteFlag;
}
