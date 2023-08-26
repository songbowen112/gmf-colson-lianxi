package com.colson.dal.dto;

import java.util.Date;

/**
 * Created by root on 2017/8/15.
 */
public class QuestionContentEssay {
    private Integer id;
    private String content;
    private String correctAnswer;
    private String analysis;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String operator;
    private Integer deleteFlag;
    private Integer analysisUseful;                                                  //解析有用
    private Integer analysisUseless;                                                 //解析无用

    public Integer getAnalysisUseful() {
        return analysisUseful;
    }

    public void setAnalysisUseful(Integer analysisUseful) {
        this.analysisUseful = analysisUseful;
    }

    public Integer getAnalysisUseless() {
        return analysisUseless;
    }

    public void setAnalysisUseless(Integer analysisUseless) {
        this.analysisUseless = analysisUseless;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
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
        return "QuestionContentEssay{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", analysis='" + analysis + '\'' +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", updateTime=" + updateTime +
                ", operator='" + operator + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", analysisUseful=" + analysisUseful +
                ", analysisUseless=" + analysisUseless +
                '}';
    }
}
