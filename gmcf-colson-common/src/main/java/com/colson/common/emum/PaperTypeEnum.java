package com.colson.common.emum;

/**
 * Created by Administrator on 2017/8/9.
 */
public enum PaperTypeEnum {
    QUIZ("QUIZ", "随堂考"), ASSIGNMENTS("ASSIGNMENTS", "课后作业"),
    COMMON_EXERCISE("COMMON_EXERCISE", "刷题"),GROUP_EXERCISE("GROUP_EXERCISE", "群刷题"),MOCK_EXAM("MOCK_EXAM", "模拟考"),
    MODEL_EXAM("MODEL_EXAM", "考前五套卷"), REAL_EXAM("REAL_EXAM", "真题"),
    GUESS_SECRET_PAPER("GUESS_SECRET_PAPER","押题密卷"),
    HIGH_FREQUENCY_TIKU("HIGH_FREQUENCY_TIKU", "高频题库"),
    ERROR_PRONE_100("ERROR_PRONE_100","易错100题"),
    PRE_EXAM_MODEL("PRE_EXAM_MODEL", "考前模拟"),;
    private String code;
    private String name;

    private PaperTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
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

    public static String getNameByCode(String code) {
        for (PaperTypeEnum c : PaperTypeEnum.values()) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c.name;
            }
        }
        return null;
    }

    public static String getCodeByName(String name){
        for (PaperTypeEnum c : PaperTypeEnum.values()) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c.code;
            }
        }
        return null;
    }

    public static boolean isDefined(String code) {
        if (code == null) {
            return false;
        }
        for (PaperTypeEnum t : PaperTypeEnum.values()) {
            if (t.code.equals(code)) {
                return true;
            }
        }
        return false;
    }

}
