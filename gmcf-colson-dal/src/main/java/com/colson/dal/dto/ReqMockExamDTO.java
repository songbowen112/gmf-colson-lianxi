package com.colson.dal.dto;

/**
 * @author: maxiao
 * @description:
 * @date: create in 14:47 2018/8/22
 * @modified By:
 * @copyright by sunlands
 */
public class ReqMockExamDTO {

    private Integer roundId; // required
    private String operateType; // required
    private Integer exerciseExamId; // required
    private Integer collegeId; // required
    private Integer subjectId; // required
    private String name; // required
    private String startTime; // required
    private String endTime; // required
    private String paperCode; // required
    private String operator; // required

    public Integer getRoundId() {
        return roundId;
    }

    public void setRoundId(Integer roundId) {
        this.roundId = roundId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Integer getExerciseExamId() {
        return exerciseExamId;
    }

    public void setExerciseExamId(Integer exerciseExamId) {
        this.exerciseExamId = exerciseExamId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
