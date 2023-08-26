package com.colson.dal.dto;

import java.util.Date;

/**
 * 题库错题对象
 *
 * @author Chen WeiJie
 * @date 2018-01-26 21:56
 **/
public class TempWrongQuestionDTO {

    /**
     * 报错学员
     */
    private String userName;

    /**
     * 错误类型
     */
    private String wrongTypeCode;

    /**
     * 描述
     */
    private String wrongContent;

    /**
     * 报错时间
     */
    private Date createTime;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWrongTypeCode() {
        return wrongTypeCode;
    }

    public void setWrongTypeCode(String wrongTypeCode) {
        this.wrongTypeCode = wrongTypeCode;
    }

    public String getWrongContent() {
        return wrongContent;
    }

    public void setWrongContent(String wrongContent) {
        this.wrongContent = wrongContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
