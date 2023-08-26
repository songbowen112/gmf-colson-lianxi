package com.colson.dal.dto;

import java.util.List;

/**
 * @author: maxiao
 * @description:
 * @date: create in 10:24 2018/10/10
 * @modified By:
 * @copyright by sunlands
 */
public class MatchRoundOperationDTO {

    private List<Integer> roundIdList;
    private Integer allRound;
    private Integer sourceTemplateId;
    private String sourceTemplateCode;
    private Integer targetTemplateId;
    private String targetTemplateCode;
    private Integer retryFlag;

    public List<Integer> getRoundIdList() {
        return roundIdList;
    }

    public void setRoundIdList(List<Integer> roundIdList) {
        this.roundIdList = roundIdList;
    }

    public Integer getAllRound() {
        return allRound;
    }

    public void setAllRound(Integer allRound) {
        this.allRound = allRound;
    }

    public Integer getSourceTemplateId() {
        return sourceTemplateId;
    }

    public void setSourceTemplateId(Integer sourceTemplateId) {
        this.sourceTemplateId = sourceTemplateId;
    }

    public String getSourceTemplateCode() {
        return sourceTemplateCode;
    }

    public void setSourceTemplateCode(String sourceTemplateCode) {
        this.sourceTemplateCode = sourceTemplateCode;
    }

    public Integer getTargetTemplateId() {
        return targetTemplateId;
    }

    public void setTargetTemplateId(Integer targetTemplateId) {
        this.targetTemplateId = targetTemplateId;
    }

    public String getTargetTemplateCode() {
        return targetTemplateCode;
    }

    public void setTargetTemplateCode(String targetTemplateCode) {
        this.targetTemplateCode = targetTemplateCode;
    }

    public Integer getRetryFlag() {
        return retryFlag;
    }

    public void setRetryFlag(Integer retryFlag) {
        this.retryFlag = retryFlag;
    }
}
