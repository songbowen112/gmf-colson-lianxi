package com.colson.dal.dto;

import java.math.BigDecimal;

/**
 * 边池试题-填空题
 *
 * @author Chen WeiJie
 * @date 2018-01-26 12:32
 **/
public class ResTempBlankDTO {


    /**
     * 题目id
     */
    private Integer id;

    /**
     * 序号
     */
    private Integer sequence;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 答案
     */
    private String answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "ResTempBlankDTO{" +
                "id=" + id +
                ", sequence=" + sequence +
                ", score=" + score +
                ", answer='" + answer + '\'' +
                '}';
    }
}

