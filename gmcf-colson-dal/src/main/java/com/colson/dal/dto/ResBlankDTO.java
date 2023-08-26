package com.colson.dal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hurw on 2017/8/22.
 */
public class ResBlankDTO implements Serializable {

    private BigDecimal studentScore;    //学生得分
    private Integer userQuestionId; //question表中ID
    private String studentAnswer; //学生答案
    private Integer questionMainId;
    private String code;// 试题code
    private Integer id;
    private Integer sequence;
    private BigDecimal score;
    private String answer;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String operator;
    private Integer deleteFlag;

    @Override
    public String toString() {
        return "ResBlankDTO{" +
                "studentScore=" + studentScore +
                ", userQuestionId=" + userQuestionId +
                ", studentAnswer='" + studentAnswer + '\'' +
                ", questionMainId=" + questionMainId +
                ", code='" + code + '\'' +
                ", id=" + id +
                ", sequence=" + sequence +
                ", score=" + score +
                ", answer='" + answer + '\'' +
                ", createTime=" + createTime +
                ", creator='" + creator + '\'' +
                ", updateTime=" + updateTime +
                ", operator='" + operator + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }

    public BigDecimal getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(BigDecimal studentScore) {
        this.studentScore = studentScore;
    }

    public Integer getUserQuestionId() {
        return userQuestionId;
    }

    public void setUserQuestionId(Integer userQuestionId) {
        this.userQuestionId = userQuestionId;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public Integer getQuestionMainId() {
        return questionMainId;
    }

    public void setQuestionMainId(Integer questionMainId) {
        this.questionMainId = questionMainId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}
