package com.colson.dal.dto;

import lombok.Data;

/**
 * 试题各维度数据
 *
 * @author suntenghao
 * @date 2020-04-07
 */
@Data
public class QuestionAllAnalysisDTO {
    private Integer questionId;
    private String questionCode;
    private String typeCode;
    private String typeName;
    private Integer difficult;
    private String difficultName;
    private Integer knowledgeNodeId;
    private String knowledgeNodeName;
    private Integer frequencyLevel;
}
