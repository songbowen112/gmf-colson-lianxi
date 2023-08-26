package com.colson.dal.dto;

import lombok.Data;

/**
 * @author: caoxuexing
 * @description:
 * @date: create in 10:17 2018/2/5
 * @modified By:
 */
@Data
public class SimilarityDTO {
    private Integer id;
    private String type;
    private Integer questionId;
    private Double similarity;
    private Integer knowledgeTreeId;
    private String questionContent1;
    private String questionContent2;
    private String questionOptions1;
    private String questionOptions2;
}
