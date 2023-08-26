package com.colson.dal.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionGroupDTO {
    private Integer groupTotalScore;
    private Integer questionAmount;
    private List<ResQuestionMainDTO> questionsList;
    private QuestionTypeDTO questionType;
}
