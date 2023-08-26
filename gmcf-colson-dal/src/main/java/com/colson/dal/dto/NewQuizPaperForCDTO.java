package com.colson.dal.dto;

/**
 * Created by tongbo on 2019/3/28.
 */
public class NewQuizPaperForCDTO {

    /**
     * 试卷id
     */
    private Integer paperId;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 试卷状态
     */
    private String quizzesPaperStatusCode;

    /**
     * 答题记录id
     */
    private Integer recordId;

    /**
     * 讲师id 对应t_user_info表主键id
     * @return
     */
    private Integer teacherId;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getQuizzesPaperStatusCode() {
        return quizzesPaperStatusCode;
    }

    public void setQuizzesPaperStatusCode(String quizzesPaperStatusCode) {
        this.quizzesPaperStatusCode = quizzesPaperStatusCode;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

}
