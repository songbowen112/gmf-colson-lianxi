package com.colson.service.impl;

import com.colson.service.RepaymentService;
import com.colson.service.context.RepaymentContext;
import com.colson.service.decorator.LoggingRepaymentDecorator;
import com.colson.service.strategy.RepaymentStrategy;
import com.colson.service.strategy.RepaymentStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepaymentServiceImpl implements RepaymentService {

    @Autowired
    private RepaymentStrategyFactory strategyFactory;

    @Override
    public void process(RepaymentContext context) {
        String capitalCode = context.getCapitalCode();
        RepaymentStrategy strategy = strategyFactory.getStrategy(capitalCode);
        if (null != strategy) {
            strategy.repayment(context);
        } else
            throw new IllegalArgumentException("No repayment strategy found for capital code: " + capitalCode);
    }

    @Override
    public void processRepayment(RepaymentContext context) {
        String capitalCode = context.getCapitalCode();
        RepaymentStrategy strategy = strategyFactory.getStrategy(capitalCode);

        // 可以选择是否应用装饰器
        if (strategy != null) {
            RepaymentStrategy loggingDecorator = new LoggingRepaymentDecorator(strategy);
            loggingDecorator.repayment(context);
        } else {
            throw new IllegalArgumentException("No repayment strategy found for capital code: " + capitalCode);
        }

    }
}
