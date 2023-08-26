package com.colson.dal.dto;

import java.math.BigDecimal;

/**
 * add by chenqiuxia 2017/11/24.
 */
public class ReqPaperStatistics {
    private String paperCode;
    private String paperName;
    private String paperType;
    private Integer questionAmount;
    private BigDecimal totalScore;
    private String operator;

    public ReqPaperStatistics() {}

    public ReqPaperStatistics(String paperCode, String paperName, String paperType, Integer questionAmount, BigDecimal totalScore, String operator) {
        this.paperCode = paperCode;
        this.paperType = paperType;
        this.paperName = paperName;
        this.questionAmount = questionAmount;
        this.totalScore = totalScore;
        this.operator = operator;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public Integer getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(Integer questionAmount) {
        this.questionAmount = questionAmount;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

}
