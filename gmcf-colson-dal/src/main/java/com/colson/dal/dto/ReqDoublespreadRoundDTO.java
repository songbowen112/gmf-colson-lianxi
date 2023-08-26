package com.colson.dal.dto;

import lombok.Data;

import java.util.List;

/**
 * @author: maxiao
 * @description:
 * @date: create in 18:27 2018/11/5
 * @modified By:
 * @copyright by sunlands
 */
@Data
public class ReqDoublespreadRoundDTO {

    private String templateCode;
    private Integer templateId;
    private List<Integer> roundIdList;

}
