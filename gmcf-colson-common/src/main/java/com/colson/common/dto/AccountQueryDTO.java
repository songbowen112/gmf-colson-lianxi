package com.colson.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 账户额度查询
 *
 * @author: miaozhizhuang
 * @date: 2020/12/8
 * @version: V1.0
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountQueryDTO {

    private String brand;

    private String uid;

}
