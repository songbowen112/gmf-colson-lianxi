package com.colson.dal.dto;

import lombok.Data;

/**
 * @Author: 郭梦丽
 * @Date: 2018/12/29 15:26
 * @Description:
 */
@Data
public class StuDoneQuestionNumDTO {

    private String stuName;

    private Integer stuId;

    private Integer questionNum;

    private String scoreRate;
}
