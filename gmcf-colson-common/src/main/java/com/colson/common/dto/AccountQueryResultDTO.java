package com.colson.common.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 账户额度查询结果
 *
 * @author: miaozhizhuang
 * @date: 2020/12/8
 * @version: V1.0
 */
@Data
public class AccountQueryResultDTO extends AccountCommonResultDTO {

    private String uid;

    private BigDecimal amount;


}
