package com.colson.dal.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */
@Data
public class ReqSavePaperDTO {

    private Integer paperId;

    private String paperName;

    private String paperType;

    private Integer knowledgeTreeId;

    private Integer examProvinceId;

    private Integer examSessionId;

    private Integer examTagId;

    private List<ReqPaperQuestionDTO> questionList;

    private List<ReqPaperHeadDTO> paperHead;

    private String code; // 试卷编号

    private Integer invalidFlag;

    private Integer subjectId;

    private Integer questionAmount;

    private BigDecimal totalScore;

    private Integer totalTime;

    private Integer currentVersion;

    private BigDecimal avgDifficultyValue;

    private String creator;

    private String operator;

    private Date createTime;

    private Date updateTime;

    private Integer deleteFlag;

    private String source;

    private String videoUrl;

}
