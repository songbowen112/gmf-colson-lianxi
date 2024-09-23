package com.colson.service.strategy;

import com.colson.service.annotation.CapitalCode;
import com.colson.service.context.RepaymentContext;
import org.springframework.stereotype.Component;

@Component
@CapitalCode("C5001")
public class NanjingRepaymentStrategy implements RepaymentStrategy {
    @Override
    public void repayment(RepaymentContext repaymentContext) {
        System.out.println(" nanjing repayment");
        // 南京银行还款业务逻辑
    }
}
