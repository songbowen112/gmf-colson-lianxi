package com.colson.dal.dto;

import lombok.Data;

/**
 * 试题组局详情
 *
 * @author suntenghao
 * @date 2020-04-03
 */
@Data
public class QuestionPaperDetailDTO {
    private Integer questionId;
    private String questionCode;
    private String paperCode;
    private String paperName;
    private String paperTypeCode;
}
