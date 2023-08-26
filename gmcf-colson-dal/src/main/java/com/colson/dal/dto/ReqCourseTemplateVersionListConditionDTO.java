package com.colson.dal.dto;

/**
 * @author: maxiao
 * @description:
 * @date: create in 12:28 2018/5/22
 * @modified By:
 * @copyright by sunlands
 */
public class ReqCourseTemplateVersionListConditionDTO {

    private Integer courseTemplateCode;
    private Integer currentVersionFlag;
    private String limitUpdateTimeStart;
    private String limitUpdateTimeEnd;
    private Integer pageNo;
    private Integer pageSize;

    public Integer getCourseTemplateCode() {
        return courseTemplateCode;
    }

    public void setCourseTemplateCode(Integer courseTemplateCode) {
        this.courseTemplateCode = courseTemplateCode;
    }

    public Integer getCurrentVersionFlag() {
        return currentVersionFlag;
    }

    public void setCurrentVersionFlag(Integer currentVersionFlag) {
        this.currentVersionFlag = currentVersionFlag;
    }

    public String getLimitUpdateTimeStart() {
        return limitUpdateTimeStart;
    }

    public void setLimitUpdateTimeStart(String limitUpdateTimeStart) {
        this.limitUpdateTimeStart = limitUpdateTimeStart;
    }

    public String getLimitUpdateTimeEnd() {
        return limitUpdateTimeEnd;
    }

    public void setLimitUpdateTimeEnd(String limitUpdateTimeEnd) {
        this.limitUpdateTimeEnd = limitUpdateTimeEnd;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
