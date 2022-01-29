package com.colson.common.emum;

public enum VacationTypeEnum {

	OVERTIME(0,"加班"),
    LEAVE(1,"事假");
    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	VacationTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public static String getName(Integer code){
        for (VacationTypeEnum q: VacationTypeEnum.values()) {
            if(q.code.equals(code)){
                return  q.name;
            }
        }
        return  null;
    }
}
