package com.colson.dal.dto;

public class QueryBufferPoolQuestionsConditionDTO {

    private String userId;

    private String status;

    private String sourceType;

    private String questionType;

    private Integer subjectId;

    private String questionSource;

    private Integer knowledgeTreeId;

    private Integer pageNo;

    private Integer pageSize;

    private String fuzzy;

    private Integer conditionCode;

    private Integer knowledgeNodeId;

    private Integer nodeInvalidFlag;

    public String getQuestionSource() {
        return questionSource;
    }

    public void setQuestionSource(String questionSource) {
        this.questionSource = questionSource;
    }

    public Integer getKnowledgeTreeId() {
        return knowledgeTreeId;
    }

    public void setKnowledgeTreeId(Integer knowledgeTreeId) {
        this.knowledgeTreeId = knowledgeTreeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getFuzzy() {
        return fuzzy;
    }

    public void setFuzzy(String fuzzy) {
        this.fuzzy = fuzzy;
    }

    public Integer getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(Integer conditionCode) {
        this.conditionCode = conditionCode;
    }

    public Integer getKnowledgeNodeId() {
        return knowledgeNodeId;
    }

    public void setKnowledgeNodeId(Integer knowledgeNodeId) {
        this.knowledgeNodeId = knowledgeNodeId;
    }

    public Integer getNodeInvalidFlag() {
        return nodeInvalidFlag;
    }

    public void setNodeInvalidFlag(Integer nodeInvalidFlag) {
        this.nodeInvalidFlag = nodeInvalidFlag;
    }
}
