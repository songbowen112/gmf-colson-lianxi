package com.colson.dal.dto;


import com.colson.common.constants.ApiConstants;

/**
 * Created by tongbo on 2018/5/17.
 */
public class ReqCourseTemplateListConditionDTO {

    private Integer knowledgeTreeId; //知识树id

    private String typeCode; //类型：MD_NORMAL精讲/MD_CONSTRUE串讲

    private String statusCode; //状态：INIT 未启用/VALID 启用/INVALID禁用

    private String templateCodeOrName; //课程模板ID或名称

    private int pageNo = ApiConstants.DEFAULT_PAGE_SIZE; //每页的pageSize

    private int pageSize = ApiConstants.DEFAULT_PAGE_NO; //当前的页码

    public Integer getKnowledgeTreeId() {
        return knowledgeTreeId;
    }

    public void setKnowledgeTreeId(Integer knowledgeTreeId) {
        this.knowledgeTreeId = knowledgeTreeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getTemplateCodeOrName() {
        return templateCodeOrName;
    }

    public void setTemplateCodeOrName(String templateCodeOrName) {
        this.templateCodeOrName = templateCodeOrName;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
