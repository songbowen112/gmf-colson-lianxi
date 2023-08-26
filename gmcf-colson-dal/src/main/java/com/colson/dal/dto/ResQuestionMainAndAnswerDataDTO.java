package com.colson.dal.dto;


import java.math.BigDecimal;

public class ResQuestionMainAndAnswerDataDTO extends ResQuestionMainDTO{
    private Double correctRate = 0.0;
    private Integer answerCount = 0;
    private Integer totalCount = 0;
    private BigDecimal totalScore = BigDecimal.ZERO;

    public Double getCorrectRate() {
        return this.correctRate;
    }

    public void setCorrectRate(Double correctRate) {
        this.correctRate = correctRate;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }
}
