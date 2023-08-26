package com.colson.dal.dto;

import lombok.Data;

/**
 * 试题组卷统计
 *
 * @author suntenghao
 * @date 2020-04-03
 */
@Data
public class QuestionPaperInfoDTO {
    private String questionCode;
    private String paperTypeCode;
    private String paperTypeName;
    private Integer amount;
}
