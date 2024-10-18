package com.colson.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 账户服务通用返回状态码DTO
 *
 * @author: miaozhizhuang
 * @date: 2020/11/10
 * @version: V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AccountCommonResultDTO {

    private Integer code;

    private String msg;

}
