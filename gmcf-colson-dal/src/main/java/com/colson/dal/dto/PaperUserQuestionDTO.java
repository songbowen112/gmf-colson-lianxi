package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description: 学员答题信息实体类
 * Created by Chen WeiJie
 * DateTime: 17-11-1 下午3:42
 */
@Data
public class PaperUserQuestionDTO {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 学员答题记录id
     */
    private Integer paperUserRecordId;

    /**
     * 题序号
     */
    private Integer sequence;

    /**
     * 题号id
     */
    private Integer questionId;

    /**
     * 子题Id, 填空题和判断论述题用到此字段
     */
    private Integer questionSubId;

    /**
     * 试题来源, 用于表明question_id来自t_question_main 还是 ent_question
     */
    private String questionIdSource;

    /**
     * 试题类型
     */
    private String questionType;

    /**
     * 阅卷方式 （机器 AI 人工）
     */
    private Integer markPaperWay;

    /**
     * 学员作答内容
     */
    private String answer;

    /**
     * 学员作答是否正确标记位
     */
    private Integer correctFlag;

    /**
     * 作答用时
     */
    private Integer answerTime;

    /**
     * 学员得分
     */
    private BigDecimal score;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 上次操作人
     */
    private String operator;

    /**
     * 逻辑删除标记位
     */
    private Integer deleteFlag;
}
