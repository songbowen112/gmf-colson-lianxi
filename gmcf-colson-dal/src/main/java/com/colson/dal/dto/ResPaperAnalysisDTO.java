package com.colson.dal.dto;

import java.util.List;

/**
 * 试卷分析
 * add by chenqiuxia 2017/10/27
 */
public class ResPaperAnalysisDTO {
    private String paperCode;
    private String paperName;
    private Integer testTakerNumber; // 累计参考人数
    private Integer pageNo; // 当前页码
    private Integer pageSize;  // 每页条目数
    private List<ResStuPaperAnswerDTO> stuPaperAnswerOverview; // 学员答题概览列表

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Integer getTestTakerNumber() {
        return testTakerNumber;
    }

    public void setTestTakerNumber(Integer testTakerNumber) {
        this.testTakerNumber = testTakerNumber;
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

    public List<ResStuPaperAnswerDTO> getStuPaperAnswerOverview() {
        return stuPaperAnswerOverview;
    }

    public void setStuPaperAnswerOverview(List<ResStuPaperAnswerDTO> stuPaperAnswerOverview) {
        this.stuPaperAnswerOverview = stuPaperAnswerOverview;
    }

}
