package com.colson.service.strategy;

import com.colson.service.context.RepaymentContext;
import org.springframework.stereotype.Component;

@Component("nanjingRepaymentStrategy")
public class NanjingRepaymentStrategy implements RepaymentStrategy {
    @Override
    public void process(RepaymentContext repaymentContext) {
        System.out.println(" nanjing repayment");
        // 南京银行还款业务逻辑
    }
}
