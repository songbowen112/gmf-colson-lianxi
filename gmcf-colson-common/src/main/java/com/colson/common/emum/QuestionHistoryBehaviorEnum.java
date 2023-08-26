package com.colson.common.emum;

/**
 * 试题操作历史记录
 */
public enum QuestionHistoryBehaviorEnum {
    UPLOAD_QUESTION("UPLOAD_QUESTION", "上传"),
    AUDIT_QUESTION_UNQUALIFIED("AUDIT_QUESTION_UNQUALIFIED", "审核为不合格"),
    AUDIT_QUESTION_QUALIFIED("AUDIT_QUESTION_QUALIFIED", "审核为合格"),
    AUDIT_QUESTION_ABANDON("AUDIT_QUESTION_ABANDON", "审核为废弃"),
    AUDIT_QUESTION_REPEAT("AUDIT_QUESTION_REPEAT", "审核为重复"),
    AUDIT_QUESTION_COMBINED("AUDIT_QUESTION_COMBINED", "合并至该题"),
    AUDIT_QUESTION_USEANALYSIS("AUDIT_QUESTION_USEANALYSIS", "合并至改题并使用解析"),
    EDIT_QUESTION_BEFORE_AUDIT("EDIT_QUESTION_BEFORE_AUDIT", "编辑边池试题"),
    EDIT_QUESTION_AFTER_AUDIT("EDIT_QUESTION_AFTER_AUDIT", "编辑主池试题"),
    EDIT_QUESTION_ADD_SESSION_PROVINCES("EDIT_Q_A_SESSION_PROVINCES", "添加题目考期省份标签"),
    EDIT_QUESTION_DEL_SESSION_PROVINCES("EDIT_Q_D_SESSION_PROVINCES", "删除题目考期省份标签"),
    HANDLE_REPORT_QUESTION_ERROR("HANDLE_REPORT_QUESTION_ERROR", "标记为出错"),
    HANDLE_REPORT_QUESTION_NO_ERROR("HANDLE_REPORT_QUESTION_NO_ERROR", "标记为未出错");

    private String code;
    private String name;

    QuestionHistoryBehaviorEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(String code) {
        for (QuestionHistoryBehaviorEnum c : QuestionHistoryBehaviorEnum.values()) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c.name;
            }
        }
        return null;
    }

    public static String getCodeByName(String name){
        for (QuestionHistoryBehaviorEnum c : QuestionHistoryBehaviorEnum.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c.code;
            }
        }
        return null;
    }

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
