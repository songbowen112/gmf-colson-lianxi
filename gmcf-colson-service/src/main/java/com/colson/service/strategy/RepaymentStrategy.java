package com.colson.service.strategy;

import com.colson.service.context.RepaymentContext;

public interface RepaymentStrategy {
    void process(RepaymentContext repaymentContext);
}
