package com.colson.dal.dto;

public class BufferPoolQuestionEditLogDTO {

    private Integer quesitonMainId;

    private String createTime;

    private String description;

    private String operator;

    private String behavior;

    private String unqualifiedDescription;

    private String unqualifiedLabel;

    private String unqualifiedLabelName;

    public Integer getQuesitonMainId() {
        return quesitonMainId;
    }

    public void setQuesitonMainId(Integer quesitonMainId) {
        this.quesitonMainId = quesitonMainId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getUnqualifiedDescription() {
        return unqualifiedDescription;
    }

    public void setUnqualifiedDescription(String unqualifiedDescription) {
        this.unqualifiedDescription = unqualifiedDescription;
    }

    public String getUnqualifiedLabel() {
        return unqualifiedLabel;
    }

    public void setUnqualifiedLabel(String unqualifiedLabel) {
        this.unqualifiedLabel = unqualifiedLabel;
    }

    public String getUnqualifiedLabelName() {
        return unqualifiedLabelName;
    }

    public void setUnqualifiedLabelName(String unqualifiedLabelName) {
        this.unqualifiedLabelName = unqualifiedLabelName;
    }
}
