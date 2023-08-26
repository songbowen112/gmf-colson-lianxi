package com.colson.dal.dto;

import lombok.Data;

/**
 * @author: caoxuexing
 * @description:
 * @date: create in 15:44 2018/2/6
 * @modified By:
 */
@Data
public class ResQuestionDTO {
    private Integer id;
    private Integer subjectId;
    private Integer parentId;
    private String type;
    private Integer knowledgeTreeId;
    private String sourceType;
}
