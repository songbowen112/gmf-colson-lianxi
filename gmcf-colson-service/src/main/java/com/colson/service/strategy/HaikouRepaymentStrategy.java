package com.colson.service.strategy;

import com.colson.service.annotation.CapitalCode;
import com.colson.service.context.RepaymentContext;
import org.springframework.stereotype.Component;

@Component
@CapitalCode("C5003")
public class HaikouRepaymentStrategy implements RepaymentStrategy {
    @Override
    public void repayment(RepaymentContext repaymentContext) {
        System.out.println(" haikou repayment");
        // 海口银行还款业务逻辑
    }
}
