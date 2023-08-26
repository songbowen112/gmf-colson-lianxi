package com.colson.dal.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/8.
 */
@Data
public class ReqSysPaperConditionDTO {

    private Integer knowledgeTreeId;

    private Integer invalidFlag;

    private String paperType;

    List<Integer> knowledgeTreeIdList;
}
