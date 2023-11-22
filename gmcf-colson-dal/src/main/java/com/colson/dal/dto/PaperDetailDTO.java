package com.colson.dal.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaperDetailDTO {
    private String paperCode;
    private String paperName;
    private String subjectName;
    private String originalSubjectName;
    private Integer knowledgeTreeId;
    private List<QuestionGroupDTO> questionGroup;
    private List<PaperHeader> paperHeaders;
}
