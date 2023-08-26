package com.colson.dal.dto;

import lombok.Data;

/**
 * 真题归属
 */
@Data
public class QuestionMainExamSessionAndProvince {
    private Integer newQuestionMainId;
    private Integer questionMainId;
    private Integer examSessionId;
    private Integer examProvinceId;
    private Integer examTagId;
}
