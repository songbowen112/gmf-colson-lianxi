package com.colson.dal.dto;

/**
 * Created by Administrator on 2017/8/9.
 */
public class ReqPreviewPaperDTO {

    private Integer paperId;

    private Integer questionIds;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(Integer questionIds) {
        this.questionIds = questionIds;
    }
}
