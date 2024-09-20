package com.colson.service.impl;

import com.colson.service.RepaymentService;
import com.colson.service.context.RepaymentContext;
import com.colson.service.strategy.RepaymentStrategy;
import com.colson.service.strategy.RepaymentStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Autowired
    private RepaymentStrategyFactory repaymentStrategyFactory;

    @Override
    public void process(RepaymentContext context) {
        RepaymentStrategy strategy = repaymentStrategyFactory.getRepaymentStrategy(context.getCapitalCode());
        if (null != strategy) {
            strategy.process(context);
        } else
            throw new RuntimeException("未找到对应的还款策略");
    }
}
