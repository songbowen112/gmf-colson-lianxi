package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Description: 全站用户的试题答题信息实体类
 * Created by Chen WeiJie
 * DateTime: 17-11-1 下午3:18
 */
@Data
public class RestQuestionAnswerStatInfo {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 试题id
     */
    private Integer questionId;

    /**
     * 平均用时
     */
    private Integer avgAnswerTime;

    /**
     * 正确率
     */
    private BigDecimal avgCorrectRate;

    /**
     * 易错答案
     */
    private String easyErrorAnswer;

    /**
     * 平均得分
     */
    private BigDecimal avgScore;

}
