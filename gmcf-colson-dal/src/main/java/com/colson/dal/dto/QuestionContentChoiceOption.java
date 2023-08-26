package com.colson.dal.dto;

import java.util.Date;

/**
 * Created by hurw on 2017/8/14.
 */
public class QuestionContentChoiceOption {
    private Integer id;
    private Integer questionId;
    private Integer sequence;
    private String optionTitle;
    private String content;
    private Integer isCorrect;
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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Integer isCorrect) {
        this.isCorrect = isCorrect;
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
        return "QuestionContentChoiceOption{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", sequence=" + sequence +
                ", optionTitle='" + optionTitle + '\'' +
                ", content='" + content + '\'' +
                ", isCorrect=" + isCorrect +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", updateTime=" + updateTime +
                ", operator='" + operator + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
