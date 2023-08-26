package com.colson.dal.dto;

import java.math.BigDecimal;

public class ResQuestionParamDTO {
    private Integer parentQuestionId;
    private String code;
    private String parentCode;
    private BigDecimal score;

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getParentQuestionId() {
        return parentQuestionId;
    }

    public void setParentQuestionId(Integer parentQuestionId) {
        this.parentQuestionId = parentQuestionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
