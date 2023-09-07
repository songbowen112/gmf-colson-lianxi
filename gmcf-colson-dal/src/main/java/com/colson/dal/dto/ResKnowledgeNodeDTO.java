package com.colson.dal.dto;

import com.colson.common.emum.OutlineRequirementEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResKnowledgeNodeDTO {
    private Integer id;
    private String serialNumber;
    private String name;
    private Integer level;  // 级别
    private Integer invalidFlag;
    private Integer parentNodeId;
    private String description;
    private String outlineRequirementCode = OutlineRequirementEnum.getCodeByName("无");  // 大纲要求code，默认无
    private String outlineRequirementName; // 大纲要求name
    private String parentSerialNumber;  // ？待定
    private Integer knowledgeTreeId;
    private String creator;
    private String operator;
    private List<ResKnowledgeNodeDTO> children;  // 层级
    private Integer knowledgeMapRelFlag; //知识点是否已关联知识图谱
    private Integer version;

    /**
     * 基于知识点维度统计数据
     * addby chenqiuxia 20180813
     */
    private ResNodeDataDTO nodeData;

    private Integer unAuditQuestionNum;

    public ResKnowledgeNodeDTO(){}
    public ResKnowledgeNodeDTO(String serialNumber, String name, Integer level, Integer knowledgeTreeId, String creator, String operator, String parentSerialNumber) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.level = level;
        this.knowledgeTreeId = knowledgeTreeId;
        this.creator = creator;
        this.operator = operator;
        this.parentSerialNumber=parentSerialNumber;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(Integer invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public Integer getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(Integer parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getOutlineRequirementCode() {
        return outlineRequirementCode;
    }

    public void setOutlineRequirementCode(String outlineRequirementCode) {
        this.outlineRequirementCode = outlineRequirementCode;
    }

    public String getOutlineRequirementName() {
        return outlineRequirementName;
    }

    public void setOutlineRequirementName(String outlineRequirementName) {
        this.outlineRequirementName = outlineRequirementName;
    }

    public List<ResKnowledgeNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ResKnowledgeNodeDTO> children) {
        this.children = children;
    }

    public String getParentSerialNumber() {
        return parentSerialNumber;
    }

    public void setParentSerialNumber(String parentSerialNumber) {
        this.parentSerialNumber = parentSerialNumber;
    }

    public Integer getKnowledgeTreeId() {
        return knowledgeTreeId;
    }

    public void setKnowledgeTreeId(Integer knowledgeTreeId) {
        this.knowledgeTreeId = knowledgeTreeId;
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

    public ResNodeDataDTO getNodeData() {
        return nodeData;
    }

    public void setNodeData(ResNodeDataDTO nodeData) {
        this.nodeData = nodeData;
    }

    public Integer getKnowledgeMapRelFlag() {
        return knowledgeMapRelFlag;
    }

    public void setKnowledgeMapRelFlag(Integer knowledgeMapRelFlag) {
        this.knowledgeMapRelFlag = knowledgeMapRelFlag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 按知识点编号比较大小
     * @param node
     * @return
     */
    public int compareTo(ResKnowledgeNodeDTO node){
        Integer number1 = Integer.parseInt(this.PostNumber());
        Integer number2 = Integer.parseInt(node.PostNumber());
        if(number1.intValue() > number2.intValue()){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * 返回知识点编号末位部分
     * @return
     */
    public String PostNumber(){
        String[] strs = this.serialNumber.split("\\.");
        return strs[strs.length-1];
    }

    public Integer getUnAuditQuestionNum() {
        return unAuditQuestionNum;
    }

    public void setUnAuditQuestionNum(Integer unAuditQuestionNum) {
        this.unAuditQuestionNum = unAuditQuestionNum;
    }
}
