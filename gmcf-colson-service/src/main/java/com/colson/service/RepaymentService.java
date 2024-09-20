package com.colson.service;

import com.colson.service.context.RepaymentContext;

public interface RepaymentService {

    /**
     * 处理还款
     * @param context
     */
    void process(RepaymentContext context);
}
