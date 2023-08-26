package com.colson.dal.dto;


import java.util.List;

/**
 * @author liujionghao
 */
public class ReqSendQuizPaperDTO {
    private Integer teachUnitId;

    private String paperCode;  // 原随堂考试卷code

    private List<ReqNodeQuestionInfoDTO> nodeQuestionInfos;

    private String source;

    private String paperName;  // 新随堂考试卷名称

    private String userName;   // 操作人

    private Integer teacherId; // 讲师id，对应t_user_info表中主键

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTeachUnitId() {
        return teachUnitId;
    }

    public void setTeachUnitId(Integer teachUnitId) {
        this.teachUnitId = teachUnitId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public List<ReqNodeQuestionInfoDTO> getNodeQuestionInfos() {
        return nodeQuestionInfos;
    }

    public void setNodeQuestionInfos(List<ReqNodeQuestionInfoDTO> nodeQuestionInfos) {
        this.nodeQuestionInfos = nodeQuestionInfos;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

}
