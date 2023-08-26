package com.colson.dal.dto;

/**
 * Created by wzz on 2017/8/17.
 */
public class ReqPaperHeadDTO {
    private String code;

    private String value;

    private Integer showFlag;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public ReqPaperHeadDTO(){

    }

    public ReqPaperHeadDTO(String code, String value, Integer showFlag) {
        this.code = code;
        this.value = value;
        this.showFlag = showFlag;
    }

    @Override
    public String toString() {
        return "ReqPaperHeadDTO{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                ", showFlag=" + showFlag +
                '}';
    }
}
