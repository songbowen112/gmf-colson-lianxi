package com.colson.dal.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * 基于知识点维度的数据统计
 */
public class ResNodeDataDTO {
    private Integer id;
    /**
     * 试题总数、真题数量、非真题数量
     */
    private Integer questionCount;
    private Integer realQuestionCount;
    private Integer normalQuestionCount;


    /**
     * 考察分值
     * 考察分值占比, 不提供百分号
     */
    private BigDecimal examineScore;
    private Double examineScoreRatio;

    /**
     * 考察次数
     * 考察次数占比, 不提供百分号
     */
    private Integer examineNum;
    private Double examineNumRatio;

    /**
     * 非末级知识点各频度子知识点数量
     */
    private Integer veryHighFrequencyCount;
    private Integer highFrequencyCount;
    private Integer middleFrequencyCount;

    /**
     * 末级知识点频度：0: 中频, 1: 高频, 2:极高频
     */
    private Integer frequencyLevel;

    private List<ResNodeQuestionDetailDTO> realQuestionDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Integer getRealQuestionCount() {
        return realQuestionCount;
    }

    public void setRealQuestionCount(Integer realQuestionCount) {
        this.realQuestionCount = realQuestionCount;
    }

    public Integer getNormalQuestionCount() {
        return normalQuestionCount;
    }

    public void setNormalQuestionCount(Integer normalQuestionCount) {
        this.normalQuestionCount = normalQuestionCount;
    }

    public BigDecimal getExamineScore() {
        return examineScore;
    }

    public void setExamineScore(BigDecimal examineScore) {
        this.examineScore = examineScore;
    }

    public Double getExamineScoreRatio() {
        return examineScoreRatio;
    }

    public void setExamineScoreRatio(Double examineScoreRatio) {
        this.examineScoreRatio = examineScoreRatio;
    }

    public Integer getExamineNum() {
        return examineNum;
    }

    public void setExamineNum(Integer examineNum) {
        this.examineNum = examineNum;
    }

    public Double getExamineNumRatio() {
        return examineNumRatio;
    }

    public void setExamineNumRatio(Double examineNumRatio) {
        this.examineNumRatio = examineNumRatio;
    }

    public Integer getVeryHighFrequencyCount() {
        return veryHighFrequencyCount;
    }

    public void setVeryHighFrequencyCount(Integer veryHighFrequencyCount) {
        this.veryHighFrequencyCount = veryHighFrequencyCount;
    }

    public Integer getHighFrequencyCount() {
        return highFrequencyCount;
    }

    public void setHighFrequencyCount(Integer highFrequencyCount) {
        this.highFrequencyCount = highFrequencyCount;
    }

    public Integer getMiddleFrequencyCount() {
        return middleFrequencyCount;
    }

    public void setMiddleFrequencyCount(Integer middleFrequencyCount) {
        this.middleFrequencyCount = middleFrequencyCount;
    }

    public Integer getFrequencyLevel() {
        return frequencyLevel;
    }

    public void setFrequencyLevel(Integer frequencyLevel) {
        this.frequencyLevel = frequencyLevel;
    }

    public List<ResNodeQuestionDetailDTO> getRealQuestionDetail() {
        return realQuestionDetail;
    }

    public void setRealQuestionDetail(List<ResNodeQuestionDetailDTO> realQuestionDetail) {
        this.realQuestionDetail = realQuestionDetail;
    }
}
