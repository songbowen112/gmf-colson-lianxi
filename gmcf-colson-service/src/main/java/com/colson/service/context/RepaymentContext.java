package com.colson.service.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentContext {

    /**
     * 资方编号
     */
    private String capitalCode;

    /**
     * 贷款编号
     */
    private String loanNo;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 还款单号
     */
    private String repaymentNo;

    /**
     * 还款利息金额
     */
    private BigDecimal repaymentInterest;

    /**
     * 还款本金金额
     */
    private BigDecimal repaymentPrincipal;

    /**
     * 还款费用金额
     */
    private BigDecimal repaymentFee;

    /**
     * 还款总额
     */
    private BigDecimal repaymentTotal;

    /**
     * 还款日期
     */
    private String repaymentDate;

    /**
     * 还款类型
     */
    private String repaymentType;

    /**
     * 还款状态
     */
    private String repaymentStatus;

    /**
     * 还款备注信息
     */
    private String repaymentRemark;



}
