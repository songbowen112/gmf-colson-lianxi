package com.colson.dal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 试卷文件查询DTO
 * @author chenliang
 * @version 1.0
 * @date 2022-07-18 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqPaperFileQueryDTO implements Serializable {

    /**
     * 试卷类型 真题：REAL_EXAM
     */
    private String paperType;

    /**
     * 试卷code
     */
    private String paperCode;

    /**
     * 考试考期
     */
    private Integer examSession;

    /**
     * 考试省份
     */
    private Integer examProvince;

    /**
     * 难度值      1：易、2：中偏易、3：中、 4：中偏难、5：难
     */
    private Integer avgDifficultyValue;

    /**
     * 上下线  0：下线  1：上线
     */
    private Integer onOffline;

    /**
     * A  、B
     */
    private Integer abPaperId;

    /**
     * 科目id
     */
    private Integer subjectId;

    /**
     * 知识树id
     */
    private Integer knowledgeTreeId;

    /**
     * 页面 默认1
     */
    private Integer pageNo = 1;

    /**
     * 每页数量 默认20
     */
    private Integer pageSize = 20;

    /**
     * 从哪里开始
     */
    private Integer pageIndex = 1;

    /**
     * id 或者试卷名称
     */
    private String idOrPaperName;
}
