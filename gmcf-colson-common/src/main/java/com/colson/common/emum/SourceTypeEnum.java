package com.colson.common.emum;

/**
 * Created by Administrator on 2017/8/9.
 */
public enum SourceTypeEnum {

    REAL_QUESTION("REAL_QUESTION", "真题"),
    MOCK_QUESTION("MOCK_QUESTION", "模拟题"),
    MATERIAL_QUESTION("MATERIAL_QUESTION", "教材题"),
    EXERCISE_QUESTION("EXERCISE_QUESTION", "练习题");


    private String code;
    private String name;

    SourceTypeEnum(String code, String name) {
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
        for (SourceTypeEnum c : SourceTypeEnum.values()) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c.name;
            }
        }
        return null;
    }

    public static String getCodeByName(String name) {
        for (SourceTypeEnum c : SourceTypeEnum.values()) {
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
        for (SourceTypeEnum t : SourceTypeEnum.values()) {
            if (t.code.equals(code)) {
                return true;
            }
        }
        return false;
    }

}
