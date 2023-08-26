package com.colson.dal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *  试题+学员答题
 *  add by chenqiuxia 2017/10/27
 */
public class ResQuestionAnswerDTO extends ResQuestionMainDTO implements Serializable{
    private String stuAnswer; // 学员答案（选择题、文字题）
    private BigDecimal stuScore; // 学员得分（选择题、文字题）
    private List<ResBlankAnswerDTO> blankAnswerList;
    private List<ResQuestionAnswerDTO> childQuestionAnswerList;

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

    public List<ResBlankAnswerDTO> getBlankAnswerList() {
        return blankAnswerList;
    }

    public void setBlankAnswerList(List<ResBlankAnswerDTO> blankAnswerList) {
        this.blankAnswerList = blankAnswerList;
    }

    public List<ResQuestionAnswerDTO> getChildQuestionAnswerList() {
        return childQuestionAnswerList;
    }

    public void setChildQuestionAnswerList(List<ResQuestionAnswerDTO> childQuestionAnswerList) {
        this.childQuestionAnswerList = childQuestionAnswerList;
    }

    @Override
    public String toString() {
        return "ResQuestionAnswerDTO{" +
                "id=" + super.getId() +
                ", code='" + super.getCode() + '\'' +
                ", sourceType='" + super.getSourceType() + '\'' +
                ", questionType='" + super.getQuestionType() + '\'' +
                ", contentType='" + super.getContentType() + '\'' +
                ", questionId=" + super.getQuestionId() +
                ", score=" + super.getScore() +
                ", difficultyValue=" + super.getDifficultyValue() +
                ", difficultyName='" + super.getDifficultyName() + '\'' +
                ", examProvince=" + super.getExamProvince() +
                ", examProvinceName='" + super.getExamProvinceName() + '\'' +
                ", examSession=" + super.getExamSession() +
                ", examSessionName='" + super.getExamSessionName() + '\'' +
                ", composeCount=" + super.getComposeCount() +
                ", currentVersion=" + super.getCurrentVersion() +
                ", parentQuestionId=" + super.getParentQuestionId() +
                ", sequence=" + super.getSequence() +
                ", invalidFlag=" + super.getInvalidFlag() +
                ", createTime=" + super.getCreateTime() +
                ", creator='" + super.getCreator() + '\'' +
                ", updateTime=" + super.getUpdateTime() +
                ", operator='" + super.getOperator() + '\'' +
                ", deleteFlag=" + super.getDeleteFlag() +
                ", outlineRequirement='" + super.getOutlineRequirement() + '\'' +
                ", content='" + super.getContent() + '\'' +
                ", correctAnswer='" + super.getCorrectAnswer() + '\'' +
                ", analysis='" + super.getAnalysis() + '\'' +
                ", questionContentChoiceOptionList=" + super.getQuestionContentChoiceOptionList() +
                ", blankAnswerList=" + blankAnswerList +
                ", questionContentEssayScorePointList="+ super.getQuestionContentEssayScorePointList() +
                ", childQuestionAnswerList=" + childQuestionAnswerList +
                ", stuAnswer="+ stuAnswer +
                ", stuScore="+ stuScore +
                '}';
    }

}
