package com.colson.service.bean;

public class UserBean {

    /**
     * 主键id
     */
    private long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别 1、男 2、女
     */
    private Integer gender;

    public UserBean() {
    }

    public UserBean(long id, String name, Integer gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    /**
     * 获取 主键id
     *
     * @return id 主键id
     */
    public long getId() {
        return this.id;
    }

    /**
     * 设置 主键id
     *
     * @param id 主键id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 获取 用户名
     *
     * @return name 用户名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置 用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取 性别 1、男 2、女
     *
     * @return gender 性别 1、男 2、女
     */
    public Integer getGender() {
        return this.gender;
    }

    /**
     * 设置 性别 1、男 2、女
     *
     * @param gender 性别 1、男 2、女
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }
}