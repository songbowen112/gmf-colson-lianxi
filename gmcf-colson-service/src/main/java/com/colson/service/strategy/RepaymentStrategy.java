package com.colson.service.strategy;

import com.colson.service.context.RepaymentContext;

/**
 * 还款策略接口
 */
public interface RepaymentStrategy {
    void process(RepaymentContext repaymentContext);
}
