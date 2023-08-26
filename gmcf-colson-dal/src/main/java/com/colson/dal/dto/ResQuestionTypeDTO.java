package com.colson.dal.dto;

/**
 * 试题类型-为与试卷预览保持一致增设
 */
public class ResQuestionTypeDTO {
    private String itemCode;
    private String itemValue;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

}
