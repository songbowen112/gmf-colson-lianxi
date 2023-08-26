package com.colson.dal.dto;

import java.util.Date;

/**
 * 知识点数据更新时间设置
 * addby chenqiuxia 20190301
 *
 */
public class ResNodeModifyDataDTO {
    private Date updateTime;
    private Integer flag;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}
