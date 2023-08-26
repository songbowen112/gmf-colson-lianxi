package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/8.
 */
@Data
public class ReqPaperConditionDTO {

    private Integer knowledgeTreeId;

    private String paperName;

    private String paperType;

    private Integer examSessionId;

    private Integer examProvinceId;

    private Integer examTagId;

    private Integer invalidFlag;

    private BigDecimal avgDifficultyValue;
}
