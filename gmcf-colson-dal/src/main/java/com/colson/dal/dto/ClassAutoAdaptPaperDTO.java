package com.colson.dal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 郭梦丽
 * @Date: 2018/12/29 13:59
 * @Description:
 */
public class ClassAutoAdaptPaperDTO {

    private Integer id;  //试卷id

    private String name;  //试卷名称

    private String subject; //科目名称

    private String provinces;   //省份集合列表

    private String exerciseType;  //试卷类型

    private String createTime; //创建时间

    private String endTime; //结束时间

    private Integer questionTopLimit;  //试题数量上限

    private Integer participateNumber;  //做题人数

    private Integer questionNum;    //做题量

    private  Integer clickNum;  //点击次数

    private Integer validFlag;  //状态值。1: 有效, 0: 无效

    private String mLinkUrl;  //微信链接，或者用来内部跳转的URL

    private String operator;  //创建者

    private String description;  //备注

    @Override
    public String toString() {
        return "ClassAutoAdaptPaperDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", provinces='" + provinces + '\'' +
                ", exerciseType='" + exerciseType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", questionTopLimit=" + questionTopLimit +
                ", participateNumber=" + participateNumber +
                ", questionNum=" + questionNum +
                ", clickNum=" + clickNum +
                ", validFlag=" + validFlag +
                ", mLinkUrl='" + mLinkUrl + '\'' +
                ", operator='" + operator + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getQuestionTopLimit() {
        return questionTopLimit;
    }

    public void setQuestionTopLimit(Integer questionTopLimit) {
        this.questionTopLimit = questionTopLimit;
    }

    public Integer getParticipateNumber() {
        return participateNumber;
    }

    public void setParticipateNumber(Integer participateNumber) {
        this.participateNumber = participateNumber;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    @JsonProperty("mLinkUrl")
    public String getMLinkUrl() {
        return mLinkUrl;
    }

    public void setMLinkUrl(String mLinkUrl) {
        this.mLinkUrl = mLinkUrl;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
