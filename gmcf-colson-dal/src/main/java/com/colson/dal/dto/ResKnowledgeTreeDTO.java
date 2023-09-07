package com.colson.dal.dto;

import java.util.Date;
import java.util.List;

public class ResKnowledgeTreeDTO {
    private Integer id;
    private String name;
    private Integer subjectId;
    private Integer languageType;//语言类型  0 中文，1 英文
    private Integer orderFillBlankAIFlag;//有序填空开启AI判分标志位,0是不开启，1是开启
    private Integer disorderFillBlankAIFlag;//无序填空开启AI判分标志位,0是不开启，1是开启
    private Integer judgeEssayAIFlag = 0; // 判断论述题开启AI判分标志位
    private Integer essayAIFlag = 0; // 文字题开启AI判分标志位
    private Date updateTime; // 更新时间
    private String operator; // 操作人263（最近一次）
    private List<ResProvinceDTO> provinceList;
    private List<ResProjectSecondDTO> projectSecondList;
    private int hasQuestionFlag; // 挂题状态
    private List<ResKnowledgeNodeDTO> children;

    public Integer getId() {
        return id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
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

    public List<ResProvinceDTO> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ResProvinceDTO> provinceList) {
        this.provinceList = provinceList;
    }

    public List<ResProjectSecondDTO> getProjectSecondList() {
        return projectSecondList;
    }

    public void setProjectSecondList(List<ResProjectSecondDTO> projectSecondList) {
        this.projectSecondList = projectSecondList;
    }
    public int getHasQuestionFlag() {
        return hasQuestionFlag;
    }

    public void setHasQuestionFlag(int hasQuestionFlag) {
        this.hasQuestionFlag = hasQuestionFlag;
    }

    public List<ResKnowledgeNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ResKnowledgeNodeDTO> children) {
        this.children = children;
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

    public Integer getLanguageType() {
        return languageType;
    }

    public void setLanguageType(Integer languageType) {
        this.languageType = languageType;
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
