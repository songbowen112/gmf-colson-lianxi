package com.colson.dal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamLabelConfirmDTO {
    List<ExamLabelDTO> addLabelList;
    List<ExamLabelDTO> delLabelList;
}
