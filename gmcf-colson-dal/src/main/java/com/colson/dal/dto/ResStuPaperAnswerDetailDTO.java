package com.colson.dal.dto;


import java.util.List;

/**
 * 学员试卷答题详情
 * add by chenqiuxia 2017/10/27
 */
public class ResStuPaperAnswerDetailDTO extends ResStuPaperAnswerDTO {
    private String paperCode; // 试卷CODE
    private Integer paperId;
    private String paperTitle; // 试卷名称
    List<ResQuestionAnswerGroupDTO> questionAnswerGroupList;

    public List<ResQuestionAnswerGroupDTO> getQuestionAnswerGroupList() {
        return questionAnswerGroupList;
    }

    public void setQuestionAnswerGroupList(List<ResQuestionAnswerGroupDTO> questionAnswerGroupList) {
        this.questionAnswerGroupList = questionAnswerGroupList;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

}
