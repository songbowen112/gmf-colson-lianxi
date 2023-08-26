package com.colson.dal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 试卷文件DTO
 * @author chenliang
 * @version 1.0
 * @date 2022-07-18 11:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReqPaperFileDTO implements Serializable {

    /**
     * 编号
     */
    private Integer id;

    /**
     * 科目id
     */
    private Integer subjectId;

    /**
     * 知识树id
     */
    private Integer knowledgeTreeId;

    /**
     * 试卷类型 真题：REAL_EXAM
     */
    private String paperType;

    /**
     * 试卷code
     */
    private String paperCode;

    /**
     * 试卷名称
     */
    private String paperName;

    /**
     * 试卷上传路径
     */
    private String paperUploadUrl;

    /**
     * 考试考期
     */
    private Integer examSession;

    /**
     * 考试省份
     */
    private Integer examProvince;

    /**
     * 考试省份名称
     */
    private String examProvinceName;

    /**
     * A  、B
     */
    private Integer abPaperId;

    /**
     * 难度值      1：易、2：中偏易、3：中、 4：中偏难、5：难
     */
    private BigDecimal avgDifficultyValue;

    /**
     * 总分
     */
    private BigDecimal totalScore;

    /**
     * 题量
     */
    private Integer questionAmount;

    /**
     * 创建人263
     */
    private String creator;

    /**
     * 修改人263
     */
    private String operator;

    /**
     * 上下线  0：下线  1：上线
     */
    private Integer onOffline;

}
