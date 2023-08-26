package com.colson.dal.dto;


import java.util.Date;

/**
 * Created by Administrator on 2017/8/23.
 */
public class PaperHeaderDTO {
    private Integer id;
    private Integer paperId;
    private String paperCode;
    private String code;
    private String title;
    private String value;
    private Integer showFlag;
    private Integer showTitleFlag;
    private Integer lineNo;
    private Integer sequence;
    private Date createTime;
    private Date updateTime;
    private String creator;
    private String operator;
    private Integer deleteFlag;

    public PaperHeaderDTO(PaperHeader paperHeader) {
        this.code = paperHeader.getCode();
        this.title = paperHeader.getTitle();
        this.showFlag = paperHeader.getShowFlag();
        this.showTitleFlag = paperHeader.getShowTitleFlag();
        this.lineNo = paperHeader.getLineNo();
        this.sequence = paperHeader.getSequence();
        this.value = paperHeader.getValue();
    }
    public PaperHeaderDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperCode() {
        return paperCode;
    }

    public void setPaperCode(String paperCode) {
        this.paperCode = paperCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(Integer showFlag) {
        this.showFlag = showFlag;
    }

    public Integer getShowTitleFlag() {
        return showTitleFlag;
    }

    public void setShowTitleFlag(Integer showTitleFlag) {
        this.showTitleFlag = showTitleFlag;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PaperHeader{" +
                "id=" + id +
                ", paperId=" + paperId +
                ", paperCode=" + paperCode +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", showFlag=" + showFlag +
                ", showTitleFlag=" + showTitleFlag +
                ", lineNo=" + lineNo +
                ", sequence=" + sequence +
                ", createTime='" + createTime + '\'' +
                ", creator='" + creator + '\'' +
                ", operator='" + operator + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
