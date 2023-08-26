package com.colson.common.emum;

/**
 * 大纲要求
 */
public enum OutlineRequirementEnum {
    NOT_REQUIRED("NOT_REQUIRED","无"),
    UNDERSTAND("UNDERSTAND","领会"),
    MEMORIZE("MEMORIZE","识记"),
    SIMPLE_APPLICATION("SIMPLE_APPLICATION","简单应用"),
    INTEGRATED_APPLICATION("INTEGRATED_APPLICATION","综合应用");

    private String code;
    private String name;

    OutlineRequirementEnum(String code, String name){
        this.code = code;
        this.name = name;
    }
    public String getCode(){
        return code;
    }
    public String getName(){
        return name;
    }
    public static String getCodeByName(String name){
        for(OutlineRequirementEnum a : OutlineRequirementEnum.values()){
            if(a.getName().equalsIgnoreCase(name)){
                return a.code;
            }
        }
        return null;
    }
    public static String getNameByCode(String code){
        for(OutlineRequirementEnum a : OutlineRequirementEnum.values()){
            if(a.getCode().equalsIgnoreCase(code)){
                return a.name;
            }
        }
        return null;
    }

    public static boolean checkInValidOutlineRequirement(String code){
        if(OutlineRequirementEnum.getCodeByName("无").equals(code)
                || OutlineRequirementEnum.getCodeByName("领会").equals(code)
                || OutlineRequirementEnum.getCodeByName("识记").equals(code)
                || OutlineRequirementEnum.getCodeByName("简单应用").equals(code)
                || OutlineRequirementEnum.getCodeByName("综合应用").equals(code))
            return false;
        return true;
    }
}