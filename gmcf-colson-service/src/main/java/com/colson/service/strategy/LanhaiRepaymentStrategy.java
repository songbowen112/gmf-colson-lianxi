package com.colson.service.strategy;

import com.colson.service.context.RepaymentContext;
import org.springframework.stereotype.Component;

@Component("lanhaiRepaymentStrategy")
public class LanhaiRepaymentStrategy implements RepaymentStrategy {
    @Override
    public void process(RepaymentContext repaymentContext) {
        System.out.println(" Lanhai repayment");
        // 蓝海银行还款业务逻辑
    }
}
