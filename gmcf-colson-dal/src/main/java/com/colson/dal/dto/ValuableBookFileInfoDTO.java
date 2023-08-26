package com.colson.dal.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by yangyang on 2017/8/10.
 */
@Data
public class ValuableBookFileInfoDTO {
    private Integer id;
    private Integer provinceId;
    private Integer subjectId;
    private Integer knowledgeTreeId;
    private String examSession;
    private String fileLink;
    private String fileShortLink;
    private Date createTime;
    private Date updateTime;
    private Integer deleteFlag;

}
