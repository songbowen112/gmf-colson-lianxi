package com.colson.dal.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 试卷分析-学员答题概览
 * add by chenqiuxia 2017/10/27
 */
public class ResStuPaperAnswerDTO {
    private Integer index; // 编号
    private Integer stuId; // 学员ID
    private String stuName;  // 学员姓名
    private String endTime;  // 交卷时间
    private Date endTimeDate;
    private String consumedTime = "";  // 用时（x小时x分x秒）
    private Integer consumedTimeInt;
    private BigDecimal fullCredit;  // 满分
    private BigDecimal stuScore;  // 学员得分
    private Integer paperUserRecordId;  // 学员答题记录ID

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getConsumedTime() {
        return consumedTime;
    }

    public void setConsumedTime(String consumedTime) {
        this.consumedTime = consumedTime;
    }

    public BigDecimal getFullCredit() {
        return fullCredit;
    }

    public void setFullCredit(BigDecimal fullCredit) {
        this.fullCredit = fullCredit;
    }

    public BigDecimal getStuScore() {
        return stuScore;
    }

    public void setStuScore(BigDecimal stuScore) {
        this.stuScore = stuScore;
    }

    public Integer getPaperUserRecordId() {
        return paperUserRecordId;
    }

    public void setPaperUserRecordId(Integer paperUserRecordId) {
        this.paperUserRecordId = paperUserRecordId;
    }

    public Date getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(Date endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public Integer getConsumedTimeInt() {
        return consumedTimeInt;
    }

    public void setConsumedTimeInt(Integer consumedTimeInt) {
        this.consumedTimeInt = consumedTimeInt;
    }

}
