package com.colson.dal.dto;

/**
 * 获取课程下随堂考的知识点及其试题数
 * @author liujionghao
 */
public class ReqNodeQuestionsDTO {
    private String paperCode;

    private Integer teachUnitId;

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public Integer getTeachUnitId() {
        return teachUnitId;
    }

    public void setTeachUnitId(Integer teachUnitId) {
        this.teachUnitId = teachUnitId;
    }
}
