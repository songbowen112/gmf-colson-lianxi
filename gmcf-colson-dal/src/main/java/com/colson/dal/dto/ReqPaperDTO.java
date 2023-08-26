package com.colson.dal.dto;

import java.math.BigDecimal;

/**
 * Created by hurw on 2017/8/9.
 * 试卷表
 */
public class ReqPaperDTO {
    private String code;	//编号（真题）
    private Integer subjectId;	//科目id（真题）
    private String name;	//名称（真题）
    private String type;	//试卷类型  （真题：REAL_EXAM 随堂考：QUIZ 课后作业：ASSIGNMENTS 刷题：COMMON_EXERCISE 模拟考：MOCK_EXAM  考前五套卷：MODEL_EXAM
    private Integer questionAmount;	//试题数（真题）
    private BigDecimal totalScore;	//试题总分数（真题）
    private String videoUrl;	//视频讲解地址
    private String source;	//试卷来源（做做平台上传EXERCISE, 教研工作台组卷EW）
    private BigDecimal avgDifficultyValue;	//难度
    private String examSession;//考期
    private String examProvinceName;//考试省份
    private String subjectName;//科目名称
    private String proj2ndName;//二级项目名称
    private String paperCode;// 试卷code

    @Override
    public String toString() {
        return "ReqPaperDTO{" +
                "code='" + code + '\'' +
                ", subjectId=" + subjectId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", questionAmount=" + questionAmount +
                ", totalScore=" + totalScore +
                ", videoUrl='" + videoUrl + '\'' +
                ", source='" + source + '\'' +
                ", avgDifficultyValue=" + avgDifficultyValue +
                ", examSession='" + examSession + '\'' +
                ", examProvinceName='" + examProvinceName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", proj2ndName='" + proj2ndName + '\'' +
                '}';
    }

    public String getExamSession() {
        return examSession;
    }

    public void setExamSession(String examSession) {
        this.examSession = examSession;
    }

    public String getExamProvinceName() {
        return examProvinceName;
    }

    public void setExamProvinceName(String examProvinceName) {
        this.examProvinceName = examProvinceName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getProj2ndName() {
        return proj2ndName;
    }

    public void setProj2ndName(String proj2ndName) {
        this.proj2ndName = proj2ndName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(Integer questionAmount) {
        this.questionAmount = questionAmount;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getAvgDifficultyValue() {
        return avgDifficultyValue;
    }

    public void setAvgDifficultyValue(BigDecimal avgDifficultyValue) {
        this.avgDifficultyValue = avgDifficultyValue;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }
}
