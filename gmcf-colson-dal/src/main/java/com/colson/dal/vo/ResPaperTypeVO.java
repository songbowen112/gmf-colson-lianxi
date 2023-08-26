package com.colson.dal.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 试卷类型VO
 * @author chenliang
 * @version 1.0
 * @date 2022-07-19 11:12
 */
@Data
public class ResPaperTypeVO implements Serializable {

    /**
     * 试卷类型
     */
    private String paperType;

    /**
     * 试卷类型描述
     */
    private String paperTypeDesc;

    /**
     * 是否展示给前端  0:不展示  1:展示
     */
    private Integer showFront;

    /**
     * 是否限制保存   0:不显示   1:限制
     */
    private Integer saveLimit;

    /**
     * 展示顺序
     */
    private Integer sortNum;

    /**
     * 是否展示考期/省份  0:不展示  1:展示
     */
    private Integer showSessionProvince;

    /**
     * 得分点校验  0:不检验  1:检验
     */
    private Integer validScore;

    /**
     * 试题类型校验   0:不检验  1:检验
     */
    private Integer validQuestionType;

    /**
     * 支持试卷文件上传   0:不支持   1:支持
     */
    private Integer supportFileUpload;
}
