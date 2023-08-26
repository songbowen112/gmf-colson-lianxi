package com.colson.dal.dto;

import java.math.BigDecimal;

/**
 * （文字题、判断论述题）得分点
 */
public class ReqScorePointDTO {
    private String content;   // 内容
    private BigDecimal score;  // 分值
    private Integer essayId;   // 文字题id

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

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

}
