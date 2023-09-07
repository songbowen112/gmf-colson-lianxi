package com.sunlands.knowledgeTree.dmo;

import java.util.Date;

/**
 * 科目&试题（文字题、判断论述题）是否可以开启AI判分关系
 * add by chenqiuxia 2017/11/2
 */
public class SubjectAIRel {
    private Integer id;
    private Integer subjectId;
    private Integer judgeEssayAIFlag;
    private Integer essayAIFlag;
    private Integer orderFillBlankAIFlag;
    private Integer disorderFillBlankAIFlag;
    private Date creatTime;
    private Date updateTime;
    private String operator;
    private Integer deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getJudgeEssayAIFlag() {
        return judgeEssayAIFlag;
    }

    public void setJudgeEssayAIFlag(Integer judgeEssayAIFlag) {
        this.judgeEssayAIFlag = judgeEssayAIFlag;
    }

    public Integer getEssayAIFlag() {
        return essayAIFlag;
    }

    public void setEssayAIFlag(Integer essayAIFlag) {
        this.essayAIFlag = essayAIFlag;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
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

    public Integer getOrderFillBlankAIFlag() {
        return orderFillBlankAIFlag;
    }

    public void setOrderFillBlankAIFlag(Integer orderFillBlankAIFlag) {
        this.orderFillBlankAIFlag = orderFillBlankAIFlag;
    }

    public Integer getDisorderFillBlankAIFlag() {
        return disorderFillBlankAIFlag;
    }

    public void setDisorderFillBlankAIFlag(Integer disorderFillBlankAIFlag) {
        this.disorderFillBlankAIFlag = disorderFillBlankAIFlag;
    }

}
