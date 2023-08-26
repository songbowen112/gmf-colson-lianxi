package com.colson.dal.dto;

/**
 * 试题编辑日志对象DTO
 *
 * @author Chen WeiJie
 * @date 2018-01-26 12:50
 **/
public class TempQuestionEditLogDTO {

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 操作人
     */
    private String operator;

    private String behavior;

    /**
     *  不合格描述
     */
    private String unqualifiedDescription;

    /**
     * 不合格标签
     */
    private String unqualifiedLabel;
    /**
     * 不合格标签中文
     */
    private String unqualifiedLabelName;

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

    @Override
    public String toString() {
        return "TempQuestionEditLogDTO{" + "createTime='" + createTime + '\'' + ", description='" + description + '\'' + ", operator='" + operator + '\'' + ", behavior='" + behavior + '\'' + ", unqualifiedDescription='" + unqualifiedDescription + '\'' + ", unqualifiedLabel='" + unqualifiedLabel + '\'' + ", unqualifiedLabelName='" + unqualifiedLabelName + '\'' + '}';
    }
}
