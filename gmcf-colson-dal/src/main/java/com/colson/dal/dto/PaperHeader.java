package com.colson.dal.dto;

import java.io.Serializable;

/**
 * 试卷头
 * Created by wzz on 2017/8/23.
 */
public class PaperHeader implements Serializable{

    private String code;
    private String title;
    private Integer showTitleFlag;
    private Integer showFlag;
    private Integer lineNo;
    private Integer sequence;
    private String value;

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

    @Override
    public String toString() {
        return "PaperHeader{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", showTitleFlag=" + showTitleFlag +
                ", showFlag=" + showFlag +
                ", lineNo=" + lineNo +
                ", sequence=" + sequence +
                ", value='" + value + '\'' +
                '}';
    }
}
