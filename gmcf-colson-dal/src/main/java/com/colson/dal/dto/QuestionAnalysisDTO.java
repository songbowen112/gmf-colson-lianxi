package com.colson.dal.dto;

import lombok.Data;

/**
 * 解析未修改时用来更新试题解析统计表
 * @Author: 郭梦丽
 * @Date: 2018/11/19 16:20
 * @Description:
 */
@Data
public class QuestionAnalysisDTO {

    private Integer id;  //解析统计表中的id

    private Integer stuId;  //学生id

    private Integer oldQuestionId; //旧试题id

    private Integer newQuestionId; //新试题id
}
