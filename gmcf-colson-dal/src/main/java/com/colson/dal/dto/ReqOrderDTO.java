package com.colson.dal.dto;

/**
 * Created by hurw on 2017/8/25.
 */
public class ReqOrderDTO {
    private String code;//排序字段：examSession考期/composeCount组卷次数/difficultyValue难度

    private String value;//排序规则：desc倒序/asc升序

    @Override
    public String toString() {
        return "ReqOrderDTO{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {

        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
