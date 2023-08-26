package com.colson.dal.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 试卷文件分页查询返回结果VO
 * @author chenliang
 * @version 1.0
 * @date 2022-07-18 11:07
 */
@Data
public class ResPaperFileVO implements Serializable {

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
    private Integer avgDifficultyValue;

    /**
     * 总分
     */
    private BigDecimal totalScore;

    /**
     * 题量
     */
    private Integer questionAmount;

    /**
     * 创建时间  yyyy-MM-dd hh:mm:ss
     */
    private String createTime;

    /**
     * 创建人263
     */
    private String creator;

    /**
     * 最新更改时间  yyyy-MM-dd hh:mm:ss
     */
    private String modifyTime;

    /**
     * 修改人263
     */
    private String operator;

    /**
     * 上下线  0：下线  1：上线
     */
    private Integer onOffline;

    /**
     * 文件类型，根据文件的后缀推出
     */
    private String fileType;

    /**
     * 考期名称
     */
    private String examSessionName;

    /**
     * AB卷名称
     */
    private String abPaperName;

}
