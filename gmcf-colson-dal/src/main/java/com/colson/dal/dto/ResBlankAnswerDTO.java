package com.colson.dal.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 填空题+学员答题
 * add by chenqiuxia 2017/10/27
 */
public class ResBlankAnswerDTO extends ResBlankDTO implements Serializable{
    private String stuAnswer;  // 学员答案（填空题）
    private BigDecimal stuScore; // 学员得分（填空题）

    public String getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(String stuAnswer) {
        this.stuAnswer = stuAnswer;
    }

    public BigDecimal getStuScore() {
        return stuScore;
    }

    public void setStuScore(BigDecimal stuScore) {
        this.stuScore = stuScore;
    }

    @Override
    public String toString() {
        return "ResBlankAnswerDTO{" +
                "questionMainId=" + super.getQuestionMainId() +
                ", id=" + super.getId() +
                ", sequence=" + super.getSequence() +
                ", score=" + super.getScore() +
                ", answer='" + super.getAnswer() + '\'' +
                ", createTime=" + super.getCreateTime() +
                ", creator='" + super.getCreator() + '\'' +
                ", updateTime=" + super.getUpdateTime() +
                ", operator='" + super.getOperator() + '\'' +
                ", deleteFlag=" + super.getDeleteFlag() +
                ", stuAnswer="+ stuAnswer +
                ", stuScore="+ stuScore +
                '}';
    }

}
