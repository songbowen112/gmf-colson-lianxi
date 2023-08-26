package com.colson.dal.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by hurw on 2017/8/22.
 */
@Data
public class ResQuestionMainDTO implements Serializable {
    private Integer id;
    private String code;
    private String sourceType;
    private String questionType;
    private String contentType;
    private Integer questionId;
    private BigDecimal score;
    private Integer knowledgeTreeId;
    private Integer mainNodeId;
    private String mainNodeName;
    private String mainNodeSerialNumber;
    private Integer viceNodeId1;
    private String viceNodeName1;
    private String viceNodeSerialNumber1;
    private Integer viceNodeId2;
    private String viceNodeName2;
    private String viceNodeSerialNumber2;
    private Integer difficultyValue;
    private Integer difficultyType;
    private String difficultyName;
    private Integer examProvince;
    private String examProvinceName;
    private Integer examSession;
    private String examSessionName;
    private Integer examTag;
    private String examTagName;
    /**
     * 「真题」多考期、省份，形如「1910上海、2004北京A卷」，中文顿号分隔
     */
    private String examSessionAndProvinces;
    /**
     * 「真题」标签列表，由examSessionAndProvinces拆分得到
     */
    private List<ExamLabelDTO> examLabelList;
    private Integer composeCount;
    private Integer currentVersion;
    private Integer parentQuestionId;
    private Integer sequence;
    private Integer invalidFlag;
    private Date createTime;
    private String creator;
    private Date updateTime;
    private String operator;
    private Integer deleteFlag;
    private String outlineRequirement;
    private String content;
    private String correctAnswer;
    private String analysis;
    private Integer analysisUseful;
    private Integer analysisUseless;
    private List<ResQuestionContentChoiceOptionDTO> questionContentChoiceOptionList;
    private List<ResBlankDTO> blankList;
    private List<ResQuestionContentEssayScorePointDTO> questionContentEssayScorePointList; // 得分点
    private List<ResQuestionMainDTO> childQuestionMainList;
    private QuestionScorePointRule questionScorePointRule;// AI判分判分点

    private Integer correctRateInteger;       //正确率 0-100数字

    private List<QuestionPaperInfoDTO> paperInfo;

    //人工阅卷使用字段
    /**
     * question表中ID
     */
    private Integer userQuestionId;

    /**
     * 试卷中的大题序号
     */
    private Integer orderNum;

    /**
     * 学生答案
     */
    private String studentAnswer;

    /**
     * 学生得分
     */
    private BigDecimal studentScore;

}
