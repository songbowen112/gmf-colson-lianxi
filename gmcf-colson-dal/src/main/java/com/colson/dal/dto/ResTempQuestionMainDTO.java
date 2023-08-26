package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 边池查询试题主表的试题详情DTO
 *
 * @author Chen WeiJie
 * @date 2018-01-26 12:17
 **/
@Data
public class ResTempQuestionMainDTO {

    /**
     * 试题id
     */
    private Integer id;

    /**
     * 是否启用
     */
    private Integer invalidFlag;

    /**
     * 科目id
     */
    private Integer subjectId;


    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 知识树所属省份
     */
    private String knowledgeTreeBelongProvince;

    /**
     * 试题类型
     */
    private String sourceType;

    /**
     * 试题题型
     */
    private String questionType;

    /**
     * 试题得分
     */
    private BigDecimal score;

    /**
     * 所属知识树
     */
    private Integer knowledgeTreeId;

    /**
     * 大纲要求
     */
    private String outlineRequirement;

    /**
     * 所属主知识点
     */
    private Integer mainNodeId;

    /**
     * 所属主知识点名称
     */
    private String mainNodeName;

    /**
     * 副知识点1
     */
    private Integer viceNodeId1;

    /**
     * 副知识点1名称
     */
    private String viceNodeName1;

    /**
     * 副知识点2
     */
    private Integer viceNodeId2;

    /**
     * 副知识点2名称
     */
    private String viceNodeName2;

    /**
     * 难度
     */
    private Integer difficultyValue;

    /**
     * 难度名称
     */
    private String difficultyName;

    /**
     * 考试省份（真题）
     */
    private Integer examProvince;

    /**
     * 省份名称
     */
    private String examProvinceName;

    /**
     * 考期
     */
    private Integer examSession;

    /**
     * 考期名称
     */
    private String examSessionName;

    /**
     * 考期
     */
    private Integer examTag;

    /**
     * 考期名称
     */
    private String examTagName;

    /**
     * 组卷次数
     */
    private Integer composeCount;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 父题目id
     */
    private Integer parentQuestionId;

    /**
     * 题序
     */
    private Integer sequence;

    /**
     * 题干内容（文字题，选择题）
     */
    private String content;

    /**
     * 正确答案 文字题选择题
     */
    private String correctAnswer;

    /**
     * 解析（问题题 选择题）
     */
    private String analysis;

    /**
     * 外键，对应t_temp_question_content_essay/t_temp_question_fill_black/t_temp_question_content_choice主键
     */
    private Integer questionId;

    /**
     * 填空题内容
     */
    private List<ResTempBlankDTO> blankList;

    /**
     * 选择题选项
     */
    private List<ResTempQuestionContentChoiceOptionDTO> questionContentChoiceOptionList;

    /**
     * 文字题得分点
     */
    private List<ResTempScorePointDTO> questionContentEssayScorePointList;

    /**
     * 判分组
     */
    private QuestionScorePointRule questionScorePointRule;

    /**
     * 综合题小题
     */
    private List<ResTempQuestionMainDTO> childQuestionMainList;

    /**
     * 试题状态
     */
    private String questionStatus;

    /**
     * 描述 如果是不合格状态，返回不合格标签和说明
     */
    private String description;

    /**
     * 示例类型
     */
    private String contentType;

    /**
     * 日志列表
     */
    private List<TempQuestionEditLogDTO> logList;

    /**
     * 试题来源
     */
    private String questionSource;

    /**
     * 删除标签
     */
    private Integer deleteFlag;

    /**
     * 纠错信息列表
     */
    private List<TempWrongQuestionDTO> wrongQuestionList;

}
