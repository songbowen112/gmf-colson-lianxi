package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 试题难度统计
 *
 * @author suntenghao
 * @date 2020-04-07
 */
@Data
public class QuestionDifficultAnalysisDTO {
    private Integer difficult;
    private String difficultName;
    private Integer amount;
    private BigDecimal rate;
}
