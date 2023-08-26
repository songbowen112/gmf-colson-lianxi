package com.colson.dal.dto;

import lombok.Data;

/**
 * Created by tongbo on 2018/2/7.
 */
@Data
public class RepeatQuestionDTO {
    private Integer repeatQuestionMainId;

    private String repeatQuestionMainCode;

    private Float repeatPercent;

    private String sourceType;
}
