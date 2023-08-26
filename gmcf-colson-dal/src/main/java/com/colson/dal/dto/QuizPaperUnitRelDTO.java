package com.colson.dal.dto;

/**
 * 多次随堂考试卷-课程关联表
 * addby chenqiuxia 20180911
 */
public class QuizPaperUnitRelDTO {
    private Integer unitId;
    private Integer paperId;    // 多次随堂考试卷id
    private String paperCode;  // 原随堂考试卷code
    private String operator;
    private Integer teacherId;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}
