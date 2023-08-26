package com.colson.service.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: songbowen
 * Date: 2022/12/28
 **/
@Data
public class DownloadPdfParamInfo {

    /**
     * 做题场景 REAL_EXAM - 真题练习
     */
    private String exerciseType;

    /**
     * 省份id，多个逗号分隔
     */
    private String provinceIds;

    /**
     * 科目id列表，多个逗号分隔
     */
    private String subjectIds;

    /**
     * 起始考期
     */
    private String beginSession;

    /**
     * 结束考期
     */
    private String endSession;

    /**
     * 0 启用，1 禁用
     */
    private Integer invalidFlag;

    /**
     * paperCode列表，多个逗号分隔
     */
    private String paperCodes;

}
