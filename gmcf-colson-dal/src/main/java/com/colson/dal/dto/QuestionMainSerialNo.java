package com.colson.dal.dto;

import java.util.Date;

/**
 * Created by hurw on 2017/8/29.
 */
public class QuestionMainSerialNo {
    private Integer id;

    private String datetime;

    private Date createTime;

    @Override
    public String toString() {
        return "QuestionMainSerialNo{" +
                "id=" + id +
                ", datetime='" + datetime + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
