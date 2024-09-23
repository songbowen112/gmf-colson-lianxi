package com.colson.service;

import com.colson.service.context.RepaymentContext;

public interface RepaymentService {

    /**
     * 处理正常还款-策略模式
     * @param context
     */
    void process(RepaymentContext context);

    /**
     * 处理正常还款-装饰者模式
     * @param context
     */
    void processRepayment(RepaymentContext context);
}
