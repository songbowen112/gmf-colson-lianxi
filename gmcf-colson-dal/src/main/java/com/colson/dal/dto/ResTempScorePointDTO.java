package com.colson.dal.dto;

import java.math.BigDecimal;

/**
 * 文字题得分点对象
 *
 * @author Chen WeiJie
 * @date 2018-01-26 12:44
 **/
public class ResTempScorePointDTO {

    /**
     * 得分点内容
     */
    private String content;

    /**
     * 得分
     */
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
