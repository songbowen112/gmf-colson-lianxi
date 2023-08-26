package com.colson.dal.dto;

import java.math.BigDecimal;

public class ResPaperQuestionRelationDTO {
    private Integer paperId;
    private String paperCode;
    private Integer questionMainId;
    private BigDecimal score;
    private Integer sequence;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public Integer getQuestionMainId() {
        return questionMainId;
    }

    public void setQuestionMainId(Integer questionMainId) {
        this.questionMainId = questionMainId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

}
