package com.colson.dal.dto;

import java.math.BigDecimal;

/**
 * 答题信息
 * add by chenqiuxia 2017/11/3
 */
public class ResPaperUserQuestionDTO {
    private Integer id;
    private Integer paperUserRecordId;
    private Integer questionId;
    private Integer questionSubId;
    private String questionType;
    private String answer;
    private BigDecimal score;
    private String operator;
    private Integer deleteFlag;
    private String questionIdSource;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaperUserRecordId() {
        return paperUserRecordId;
    }

    public void setPaperUserRecordId(Integer paperUserRecordId) {
        this.paperUserRecordId = paperUserRecordId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionSubId() {
        return questionSubId;
    }

    public void setQuestionSubId(Integer questionSubId) {
        this.questionSubId = questionSubId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getQuestionIdSource() {
        return questionIdSource;
    }

    public void setQuestionIdSource(String questionIdSource) {
        this.questionIdSource = questionIdSource;
    }

}
