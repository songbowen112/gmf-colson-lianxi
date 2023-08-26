package com.colson.dal.dto;

/**
 * 查询答题信息-参数
 * add by chenqiuxia 2017/11/3
 */
public class ReqPaperUserQuestionDTO {
    private Integer questionId;
    private Integer questionSubId;
    private Integer paperUserRecordId;
    private String stuId;  // 分表后缀为学员id后两位

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getQuestionSubId() {
        return questionSubId;
    }

    public void setQuestionSubId(Integer questionSubId) {
        this.questionSubId = questionSubId;
    }

    public Integer getPaperUserRecordId() {
        return paperUserRecordId;
    }

    public void setPaperUserRecordId(Integer paperUserRecordId) {
        this.paperUserRecordId = paperUserRecordId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }


}
