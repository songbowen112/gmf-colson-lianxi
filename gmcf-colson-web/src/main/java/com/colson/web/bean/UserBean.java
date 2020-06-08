package com.colson.web.bean;

public class UserBean {

    private String name;

    private Integer gender;

    public UserBean() {
    }

    public UserBean(String name, Integer gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
