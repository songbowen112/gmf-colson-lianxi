package com.colson.dal.dto;

/**
 * @Author: 郭梦丽
 * @Date: 2018/11/16 10:57
 * @Description:
 */
public class QuestionAnalysisContentDTO {

    private Integer id; //试题id

    private String code;   //试题code

    private String analysis;    //解析

    private Integer analysisUseful;    //解析有用

    private Integer analysisUseless;    //解析无用

    @Override
    public String toString() {
        return "QuestionAnalysisContentDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", analysis='" + analysis + '\'' +
                ", analysisUseful=" + analysisUseful +
                ", analysisUseless=" + analysisUseless +
                '}';
    }

    public Integer getAnalysisUseful() {
        return analysisUseful;
    }

    public void setAnalysisUseful(Integer analysisUseful) {
        this.analysisUseful = analysisUseful;
    }

    public Integer getAnalysisUseless() {
        return analysisUseless;
    }

    public void setAnalysisUseless(Integer analysisUseless) {
        this.analysisUseless = analysisUseless;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public QuestionAnalysisContentDTO() {
    }

    public QuestionAnalysisContentDTO(Integer id, String code, String analysis) {
        this.id = id;
        this.code = code;
        this.analysis = analysis;
    }
}
