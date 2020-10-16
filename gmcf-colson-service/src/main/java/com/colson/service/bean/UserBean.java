package com.colson.service.bean;

public class UserBean {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别 1、男 2、女
     */
    private Integer gender;

    /**
     * 身份证号
     */
    private String idCardNo;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String region;

    /**
     * 详细地址
     */
    private String detailAddress;

    public UserBean() {
    }

    public UserBean(String name, Integer gender, String idCardNo) {
        this.name = name;
        this.gender = gender;
        this.idCardNo = idCardNo;
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

    /**
     * 获取 身份证号
     *
     * @return idCardNo 身份证号
     */
    public String getIdCardNo() {
        return this.idCardNo;
    }

    /**
     * 设置 身份证号
     *
     * @param idCardNo 身份证号
     */
    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    /**
     * 获取 手机号
     *
     * @return phoneNo 手机号
     */
    public String getPhoneNo() {
        return this.phoneNo;
    }

    /**
     * 设置 手机号
     *
     * @param phoneNo 手机号
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * 获取 省
     *
     * @return province 省
     */
    public String getProvince() {
        return this.province;
    }

    /**
     * 设置 省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取 市
     *
     * @return city 市
     */
    public String getCity() {
        return this.city;
    }

    /**
     * 设置 市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取 区
     *
     * @return region 区
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * 设置 区
     *
     * @param region 区
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 获取 详细地址
     *
     * @return detailAddress 详细地址
     */
    public String getDetailAddress() {
        return this.detailAddress;
    }

    /**
     * 设置 详细地址
     *
     * @param detailAddress 详细地址
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    /**
     * 获取 主键id
     *
     * @return id 主键id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * 设置 主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }
}