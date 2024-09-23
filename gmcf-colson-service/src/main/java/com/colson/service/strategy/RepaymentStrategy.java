package com.colson.service.strategy;

import com.colson.service.context.RepaymentContext;

/**
 * 正常还款策略接口
 */
public interface RepaymentStrategy {
    void repayment(RepaymentContext repaymentContext);
}
