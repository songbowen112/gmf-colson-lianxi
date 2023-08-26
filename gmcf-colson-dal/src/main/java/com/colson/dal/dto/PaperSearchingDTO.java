package com.colson.dal.dto;

/**
 * Created by lm on 2017/8/8.
 */

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * 条件搜索试卷DTO.
 */
@Data
public class PaperSearchingDTO {
    private Integer knowledgeTreeId;//知识树id
    private Set<Integer> knowledgeTreeIds;//知识树id列表
    private Integer subjectId;//科目id
    private Integer provinceId;//省份id
    private Integer projectSecondId;//二级项目id
    private String paperNameOrCode;//试卷名称或编号
    private String paperType;//试卷类型
    private Integer examSessionId;//考期
    private List<Integer> examSessionIds;//考期列表
    private Integer examProvinceId;//考试省份
    private Integer examTagId;//考试省份
    private String difficultyValue;//难度值
    private Integer invalidFlag;//试卷状态
    private BigDecimal minDifficulty;//最小难度值
    private BigDecimal maxDifficulty;//最大难度值
    private String source;//试卷来源 教研工作台组卷EW/讲师工作台组TW
    private String operator;//操作人263
    private Integer pageNo = 1;//当前页码
    private Integer pageSize = 10;//每页显示的条目数
    private Integer pageIndex;


    public PaperSearchingDTO() {}

    public PaperSearchingDTO(Integer knowledgeTreeId, Integer examProvinceId, Integer examSessionId, Integer examTagId) {
        this.knowledgeTreeId = knowledgeTreeId;
        this.examProvinceId = examProvinceId;
        this.examSessionId = examSessionId;
        this.examTagId = examTagId;
    }

}
