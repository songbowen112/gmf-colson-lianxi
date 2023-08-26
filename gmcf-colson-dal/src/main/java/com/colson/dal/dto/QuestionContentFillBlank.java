package com.colson.dal.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hurw on 2017/8/15.
 */
public class QuestionContentFillBlank {
    private Integer id;
    private Integer sequence;
    private BigDecimal score;
    private String answer;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String operator;
    private Integer deleteFlag;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public String toString() {
        return "QuestionContentFillBlank{" +
                "id=" + id +
                ", sequence=" + sequence +
                ", score=" + score +
                ", answer='" + answer + '\'' +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", updateTime=" + updateTime +
                ", operator='" + operator + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
