package com.colson.dal.dto;

import lombok.Data;

/**
 * 首页试题数据
 * @author liujionghao
 */
@Data
public class AllQuestionDetails {
    /**
     * 试题主池数量
     */
    private Integer totalQuestions;

    /**
     * 待审核试题数
     */
    private Integer notAuditQuestions;

    /**
     * 真题数量
     */
    private Integer realQuestions;

    /**
     * 普通试题数量
     */
    private Integer normalQuestions;

    /**
     * 数据截止时间
     */
    private String endDate;
}
