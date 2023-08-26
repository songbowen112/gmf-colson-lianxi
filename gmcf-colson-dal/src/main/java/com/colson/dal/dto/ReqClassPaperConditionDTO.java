package com.colson.dal.dto;

import lombok.Data;

/**
 * @Author: 郭梦丽
 * @Date: 2018/12/29 13:54
 * @Description:
 */
@Data
public class ReqClassPaperConditionDTO extends ReqAutoAdaptPaperConditionDTO{

    private Integer subjectId;  //科目id

    private String operators; //创建者名称集合，班主任工作台自适应组卷使用

    private String createTime;  //试卷创建时间，班主任工作台自适应组卷使用
}
