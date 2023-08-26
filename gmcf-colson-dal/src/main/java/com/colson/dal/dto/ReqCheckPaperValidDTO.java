package com.colson.dal.dto;

import java.util.List;

/**
 * Created by tongbo on 2018/5/17.
 */
public class ReqCheckPaperValidDTO {

    private Integer knowledgeTreeId;

    private String paperType;

    private Integer invalidFlag = 0;

    private List<String> paperCodeList;

    public Integer getKnowledgeTreeId() {
        return knowledgeTreeId;
    }

    public void setKnowledgeTreeId(Integer knowledgeTreeId) {
        this.knowledgeTreeId = knowledgeTreeId;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public Integer getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(Integer invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public List<String> getPaperCodeList() {
        return paperCodeList;
    }

    public void setPaperCodeList(List<String> paperCodeList) {
        this.paperCodeList = paperCodeList;
    }

}
