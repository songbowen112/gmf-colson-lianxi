package com.colson.dal.model;

/**
    * 支付单表
    */
public class PaymentEntity {
    /**
    * 主键id
    */
    private Integer id;

    /**
    * 支付单号
    */
    private String serial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}