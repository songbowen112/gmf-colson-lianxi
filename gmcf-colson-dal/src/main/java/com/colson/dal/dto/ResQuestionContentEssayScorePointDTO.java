package com.colson.dal.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResQuestionContentEssayScorePointDTO implements Serializable{
    private String content;
    private BigDecimal score;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ResQuestionContentEssayScorePointDTO{" +
                "content=" + content +
                ", score=" + score +
                '}';
    }

}
