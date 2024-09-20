package com.colson.service.strategy;

import com.colson.service.context.RepaymentContext;
import org.springframework.stereotype.Component;

@Component("haikouRepaymentStrategy")
public class HaikouRepaymentStrategy implements RepaymentStrategy {
    @Override
    public void process(RepaymentContext repaymentContext) {
        System.out.println(" haikou repayment");
        // 海口银行还款业务逻辑
    }
}
