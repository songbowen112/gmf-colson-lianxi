package com.colson.dal.dto;

/**
 * Created by lm on 2017/8/8.
 */

import com.colson.common.utils.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 试卷列表DTO.
 */
@Data
public class PaperInfoDTO {
    private Integer paperId;//试卷id

    private Integer subjectId;//科目id

    private Integer knowledgeTreeId;//知识树id

    private String code;//试卷编号(对外)

    private String paperName;//试卷名称

    private String paperType;//试卷类型

    private Integer invalidFlag;//试卷状态（0：启用，1：禁用（默认））

//    private Integer couldBeusedByStudents; // add by chenqiuxia 题库改版 - 真题练习，新增字段

    private Integer questionAmount;//题量

    private BigDecimal totalScore;//总分

    private Integer examProvinceId;//考试省份id

    private String examProvinceName;//考试省份

    private String examSessionName;//考期名称

    private Integer examSessionId;//考期id（真题）

    private String examTagName;//AB卷名称

    private Integer examTagId;//AB卷id（真题）

    private BigDecimal avgDifficultyValue;//平均难度值

    private String avgDifficultyName;//平均难度值

    private Date createTime;//创建时间

    private String creator;//创建人263

    private Date updateTime;//更新时间

    private String operator;//操作人263

    private String mlinkUrl;//Mlink地址

    private Integer sendStatus;//消息是否发送到群状态

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateTime() {
        return createTime;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getUpdateTime() {
        return updateTime;
    }
}
