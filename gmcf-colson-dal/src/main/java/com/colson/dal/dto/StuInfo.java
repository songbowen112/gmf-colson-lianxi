package com.colson.dal.dto;

import java.io.Serializable;

/**
 * Created by tongbo on 2017/3/27.
 */
public class StuInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5840379748422166746L;

	private Integer stuId;

    private String name;

    private String phone;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
