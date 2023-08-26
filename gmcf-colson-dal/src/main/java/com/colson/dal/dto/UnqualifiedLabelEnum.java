package com.colson.dal.dto;

import java.util.Objects;

/**
 * 不合格标签枚举类
 *
 * @author Chen WeiJie
 * @date 2018-02-06 18:19
 **/
public enum UnqualifiedLabelEnum {

    KNOWLEDGE_NODE_WRONG("KNOWLEDGE_NODE_WRONG", "知识点挂错"),
    EXPERT_CONTENT_WRONG("EXPERT_CONTENT_WRONG", "解析错误"),
    SCORE_WRONG("SCORE_WRONG", "分值错误"),
    QUESTION_TYPE_WRONG("QUESTION_TYPE_WRONG", "题型错误"),
    FORMAT_WRONG("FORMAT_WRONG", "格式错误"),
    QUESTION_OTHER_WRONG("QUESTION_OTHER_WRONG", "其他");

    UnqualifiedLabelEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }


    /**
     * 根据code 查询名称
     *
     * @param code
     * @return
     */
    public static String getNameByCode(String code) {

        UnqualifiedLabelEnum[] labelEnumArr = UnqualifiedLabelEnum.values();
        for (UnqualifiedLabelEnum labelEnum : labelEnumArr) {
            if (Objects.equals(labelEnum.code, code)) {
                return labelEnum.name;
            }
        }
        return null;
    }

    /**
     * code
     */
    private String code;

    /**
     * 名称
     */
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
}
