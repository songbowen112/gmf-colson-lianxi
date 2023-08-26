package com.colson.dal.dto;

public class ResQuestionAnswerNum {
    private Integer questionMainId;
    private Integer totalAnswerNum;
    private Double correctRate = 0.0;
    private Integer answerCount;

    public Double getCorrectRate() {
        return correctRate;
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

    public Integer getQuestionMainId() {
        return questionMainId;
    }

    public void setQuestionMainId(Integer questionMainId) {
        this.questionMainId = questionMainId;
    }

    public Integer getTotalAnswerNum() {
        return totalAnswerNum;
    }

    public void setTotalAnswerNum(Integer totalAnswerNum) {
        this.totalAnswerNum = totalAnswerNum;
    }

}
