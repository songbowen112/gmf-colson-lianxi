package com.colson.dal.dto;

import lombok.Data;

import java.util.List;

/**
 * @Author: 郭梦丽
 * @Date: 2018/12/29 13:56
 * @Description:
 */
@Data
public class ResClassAutoAdaptPaperDTO {

    private Integer pageSize;  //每页条目数

    private Integer pageNo;  //当前页码

    private Integer total; //试卷总数量

    private List<ClassAutoAdaptPaperDTO> rows;
}
