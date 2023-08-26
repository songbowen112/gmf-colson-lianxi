package com.colson.dal.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/8.
 */
public class ResPaperDTO {

    private Integer paperId;
    private String paperType;
    private String paperName;
    private String examProvince;
    private String examSession;
    private BigDecimal avgDifficultyValue;
    private BigDecimal totalScore;
    private Integer questionAmount;
    private String code;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String operator;
    private Integer invalidFlag;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getExamProvince() {
        return examProvince;
    }

    public void setExamProvince(String examProvince) {
        this.examProvince = examProvince;
    }

    public String getExamSession() {
        return examSession;
    }

    public void setExamSession(String examSession) {
        this.examSession = examSession;
    }

    public BigDecimal getAvgDifficultyValue() {
        return avgDifficultyValue;
    }

    public void setAvgDifficultyValue(BigDecimal avgDifficultyValue) {
        this.avgDifficultyValue = avgDifficultyValue;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(Integer questionAmount) {
        this.questionAmount = questionAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(Integer invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ResPaperDTO() {
    }

    public ResPaperDTO(Integer paperId, String paperType, String paperName, String examProvince, String examSession, BigDecimal avgDifficultyValue, BigDecimal totalScore, Integer questionAmount, String code, String creator, String operator, Integer invalidFlag) {
        this.paperId = paperId;
        this.paperType = paperType;
        this.paperName = paperName;
        this.examProvince = examProvince;
        this.examSession = examSession;
        this.avgDifficultyValue = avgDifficultyValue;
        this.totalScore = totalScore;
        this.questionAmount = questionAmount;
        this.code = code;
        this.creator = creator;
        this.operator = operator;
        this.invalidFlag = invalidFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResPaperDTO that = (ResPaperDTO) o;

        if (paperId != null ? !paperId.equals(that.paperId) : that.paperId != null) return false;
        if (paperType != null ? !paperType.equals(that.paperType) : that.paperType != null) return false;
        if (paperName != null ? !paperName.equals(that.paperName) : that.paperName != null) return false;
        if (examProvince != null ? !examProvince.equals(that.examProvince) : that.examProvince != null) return false;
        if (examSession != null ? !examSession.equals(that.examSession) : that.examSession != null) return false;
        if (avgDifficultyValue != null ? !avgDifficultyValue.equals(that.avgDifficultyValue) : that.avgDifficultyValue != null)
            return false;
        if (totalScore != null ? !totalScore.equals(that.totalScore) : that.totalScore != null) return false;
        if (questionAmount != null ? !questionAmount.equals(that.questionAmount) : that.questionAmount != null)
            return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (operator != null ? !operator.equals(that.operator) : that.operator != null) return false;
        return invalidFlag != null ? invalidFlag.equals(that.invalidFlag) : that.invalidFlag == null;
    }

    @Override
    public int hashCode() {
        int result = paperId != null ? paperId.hashCode() : 0;
        result = 31 * result + (paperType != null ? paperType.hashCode() : 0);
        result = 31 * result + (paperName != null ? paperName.hashCode() : 0);
        result = 31 * result + (examProvince != null ? examProvince.hashCode() : 0);
        result = 31 * result + (examSession != null ? examSession.hashCode() : 0);
        result = 31 * result + (avgDifficultyValue != null ? avgDifficultyValue.hashCode() : 0);
        result = 31 * result + (totalScore != null ? totalScore.hashCode() : 0);
        result = 31 * result + (questionAmount != null ? questionAmount.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (invalidFlag != null ? invalidFlag.hashCode() : 0);
        return result;
    }
}
