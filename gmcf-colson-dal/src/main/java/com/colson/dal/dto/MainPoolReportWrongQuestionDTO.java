package com.colson.dal.dto;

import lombok.Data;

/**
 * 描述:
 * date : create in 19:34 2018/1/26
 * modified by :
 *
 * @author subo
 */
@Data
public class MainPoolReportWrongQuestionDTO {
    private Integer id;
    private Integer stuId;
    private String errorDetail;
    private Integer questionId;
    private Integer status;
}
