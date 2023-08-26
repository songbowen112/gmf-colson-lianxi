package com.colson.dal.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 试题-答题-组
 * add by chenqiuxia 2017/10/27
 */
public class ResQuestionAnswerGroupDTO {
    private ResQuestionTypeDTO questionType; // 试题类型
    private Integer questionAmount; // 题目数量
    private Integer totalScore;  // 满分 
    // TODO: 2017/11/5 满分怎么算？不应该是Integer吧，顶多double
    private BigDecimal stuScore;  // 学员得分
    private List<ResQuestionAnswerDTO> questionAnswerList;

    public ResQuestionTypeDTO getQuestionType() {
        return questionType;
    }

    public void setQuestionType(ResQuestionTypeDTO questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(Integer questionAmount) {
        this.questionAmount = questionAmount;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public BigDecimal getStuScore() {
        return stuScore;
    }

    public void setStuScore(BigDecimal stuScore) {
        this.stuScore = stuScore;
    }

    public List<ResQuestionAnswerDTO> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List<ResQuestionAnswerDTO> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

}
