package com.colson.dal.dto;


import java.util.Date;
import java.util.List;

/**
 * @author liujionghao
 */
public class ResQuizDetailsDTO {
    private Integer knowledgeTreeId;

    private Integer paperId;

    private String paperName;

    private List<ResQuestionMainAndAnswerDataDTO> questionsList;

    private List<PaperHeader> paperHeaders;

    private Date createTime;

    private Integer total;

    private Integer pageSize;

    private Integer pageNo;

    private List<List<ResQuestionMainAndAnswerDataDTO>> questionTypeDetailList;

    public List<List<ResQuestionMainAndAnswerDataDTO>> getQuestionTypeDetailList() {
        return questionTypeDetailList;
    }

    public void setQuestionTypeDetailList(List<List<ResQuestionMainAndAnswerDataDTO>> questionTypeDetailList) {
        this.questionTypeDetailList = questionTypeDetailList;
    }

    public Integer getKnowledgeTreeId() {
        return knowledgeTreeId;
    }

    public void setKnowledgeTreeId(Integer knowledgeTreeId) {
        this.knowledgeTreeId = knowledgeTreeId;
    }

    public List<PaperHeader> getPaperHeaders() {
        return paperHeaders;
    }

    public void setPaperHeaders(List<PaperHeader> paperHeaders) {
        this.paperHeaders = paperHeaders;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public List<ResQuestionMainAndAnswerDataDTO> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<ResQuestionMainAndAnswerDataDTO> questionsList) {
        this.questionsList = questionsList;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

}
