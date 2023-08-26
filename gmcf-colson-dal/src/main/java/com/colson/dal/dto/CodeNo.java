package com.colson.dal.dto;

/**
 * Created by Administrator on 2017/8/11.
 */
public class CodeNo {
    private Integer id;
    private String dateTime;

    public CodeNo(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
