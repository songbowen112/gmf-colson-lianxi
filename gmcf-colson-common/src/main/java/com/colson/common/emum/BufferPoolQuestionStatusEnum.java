package com.colson.common.emum;

public enum BufferPoolQuestionStatusEnum {

    NOT_SUBMIT("NOT_SUBMIT", "未提交审核"),
    NOT_AUDIT("NOT_AUDIT", "待审核"),
    UNQUALIFIED("UNQUALIFIED", "不合格"),
    QUALIFIED("QUALIFIED", "合格"),
    ABANDON("ABANDON", "废弃"),
    REPEAT("REPEAT", "重复"),
    COMBINED("COMBINED", "合并至该题"),
    USEANALYSIS("USEANALYSIS", "合并至改题并使用解析");

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 构造方法
    BufferPoolQuestionStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (BufferPoolQuestionStatusEnum c : BufferPoolQuestionStatusEnum.values()) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c.name;
            }
        }
        return null;
    }

    public static String getCodeByName(String name){
        for (BufferPoolQuestionStatusEnum c : BufferPoolQuestionStatusEnum.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c.code;
            }
        }
        return null;
    }


}
