package com.colson.dal.dto;

public class QuestionDetailsDTO {

    private Integer totalQuestions; // 上传试题数
    private Integer notAuditQuestions; // 审核中试题数
    private Integer qualifiedQuestions; // 合格试题数
    private Integer unqualifiedTimes; // 不合格试题次数

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getNotAuditQuestions() {
        return notAuditQuestions;
    }

    public void setNotAuditQuestions(Integer notAuditQuestions) {
        this.notAuditQuestions = notAuditQuestions;
    }

    public Integer getQualifiedQuestions() {
        return qualifiedQuestions;
    }

    public void setQualifiedQuestions(Integer qualifiedQuestions) {
        this.qualifiedQuestions = qualifiedQuestions;
    }

    public Integer getUnqualifiedTimes() {
        return unqualifiedTimes;
    }

    public void setUnqualifiedTimes(Integer unqualifiedTimes) {
        this.unqualifiedTimes = unqualifiedTimes;
    }
}
